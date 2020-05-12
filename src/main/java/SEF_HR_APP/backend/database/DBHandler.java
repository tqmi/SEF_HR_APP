package SEF_HR_APP.backend.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.datamodels.activity.ActivityPayOptionLink;
import SEF_HR_APP.backend.datamodels.activity.ActivityStatus;
import SEF_HR_APP.backend.datamodels.activity.MonthType;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import SEF_HR_APP.backend.datamodels.user.AccountType;
import SEF_HR_APP.backend.datamodels.user.Position;
import SEF_HR_APP.backend.datamodels.user.Seniority;
import SEF_HR_APP.backend.datamodels.user.User;

public class DBHandler {

    private static final String dbURL = "jdbc:derby:./db/MyDB;";
    private static final String adminFile = "./setup/admin.stp";
    private static String adminusername;
    private static String adminpassword;

    private static Connection connection;
    private static Statement stmt;


    /**
     * Initialize database connection or create new database if it not exists
     * @return
     */
    public synchronized static boolean connectDB() {

        try {
            System.out.println("Connecting to db ...");
            connection = DriverManager.getConnection(dbURL);
            System.out.println("Connection successful!");
            return true;
        } catch (SQLException e) {
            try {
                System.out.println("DB not found! Creating new db ...");
                createDB();
                return true;
            } catch (SQLException e1) {
                System.out.println("Error creating new db!");
                System.out.println(e.getMessage());
                return false;
            }
        }

    }

    /**
     * Creates the database with all its tables
     * @throws SQLException
     */
    private synchronized static void createDB() throws SQLException {
        connection = DriverManager.getConnection(dbURL + "create=true;");
        System.out.println("DB created! Adding User table ...");
        createUserTable();
        System.out.println("User table added! Adding Pay Option table ...");
        createPayOptionTable();
        System.out.println("Pay Option table added! Adding Activity table ...");
        createActivityInformationTable();
        System.out.println("Activity table added! Adding Link ...");
        createLinkTable();
        System.out.println("Link table added! Adding admin account ...");
        addAdminAccountToTable();
    }
    
    /**
     * Closes the db connection
     */
    public synchronized static void close(){
        try{
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        }catch(SQLException ex){
            if (ex.getSQLState().equals("XJ015")) {
                System.out.println("DB shutdown normally");
            } else {
                ex.printStackTrace();
            }
        }

    }



    /**
     * Inserts addmin account in User table
     */
    private synchronized static void addAdminAccountToTable() {

        readAdminAccountDetails();

        try {
            stmt = connection.createStatement();
            User admin = new User("admin", Position.ADMIN, "admin", Seniority.JUNIOR, 0, 0, AccountType.SUPERVISOR_OPERATOR);
            admin.setUsername(adminusername);
            admin.setPassword(adminpassword);
            String[] fieldNames = admin.getFieldsName();
            StringBuilder sql = new StringBuilder("INSERT INTO Users (");

            for(int i = 0 ;i < fieldNames.length -1 ; i++){
                sql.append(fieldNames[i] + ",");
            }
            sql.append(fieldNames[fieldNames.length-1] + ") VALUES (");
            String[] fields = admin.getFieldsData();
            for (int i = 0; i < fields.length - 1; i++) {
                sql.append(fields[i] + ",");
            }
            sql.append(fields[fields.length - 1] + ")");
            System.out.println(sql.toString()+"\n");
            stmt.executeUpdate(sql.toString());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ;
        }

    }

    /**
     * Checks if there exists a setup file for admin account credentials and loads them
     */
    private synchronized static void readAdminAccountDetails() {
        try {
            Scanner sc = new Scanner(new FileInputStream(adminFile));
            if(sc.hasNext())
                adminusername = sc.next();
            if(sc.hasNext())
                adminpassword = sc.next();

            if(adminusername == null || adminpassword == null)
                adminUseDefaults();
            return;
        
        } catch (FileNotFoundException e) {
            adminUseDefaults();
            return;
        }
        
    }

    /**
     * Set defaults for admin credentials
     */
    private synchronized static void adminUseDefaults(){
        adminusername = "admin";
        adminpassword = "admin";
    }

    /**
     * Create user table
     */
    private synchronized static void createUserTable() {
        try {
            stmt = connection.createStatement();
            
            StringBuilder sql = new StringBuilder( "CREATE TABLE Users (\nid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY\n");
            User dummy = new User();
            String[] fieldTypes = dummy.getFieldsType();
            String[] fieldNames = dummy.getFieldsName();

            for(int i = 0 ; i < fieldNames.length ; i++){
                sql.append(","+fieldNames[i] + " " + fieldTypes[i] +"\n");
            }
            sql.append(")"); 

            System.out.println(sql.toString()+"\n");
            stmt.executeUpdate(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create pay options table
     */
    private synchronized static void createPayOptionTable() {
        try {
            stmt = connection.createStatement();
            
            StringBuilder sql = new StringBuilder( "CREATE TABLE PayOptions (\nid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY\n");
            PayOption dummy = new PayOption();
            String[] fieldTypes = dummy.getFieldsType();
            String[] fieldNames = dummy.getFieldsName();

            for(int i = 0 ; i < fieldNames.length ; i++){
                sql.append(","+fieldNames[i] + " " + fieldTypes[i] +"\n");
            }
            sql.append(")"); 

            System.out.println(sql.toString()+"\n");
            stmt.executeUpdate(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create activity information table
     */
    private static void createActivityInformationTable() {
        try {
            stmt = connection.createStatement();
            
            StringBuilder sql = new StringBuilder( "CREATE TABLE Activities (\nid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY\n");
            ActivityInformation dummy = new ActivityInformation();
            String[] fieldTypes = dummy.getFieldsType();
            String[] fieldNames = dummy.getFieldsName();

            for(int i = 0 ; i < fieldNames.length ; i++){
                sql.append(","+fieldNames[i] + " " + fieldTypes[i] +"\n");
            }
            sql.append(")"); 

            System.out.println(sql.toString()+"\n");
            stmt.executeUpdate(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create link table for n to n connection between activity and pay options tables
     */
    private static void createLinkTable() {
        try {
            stmt = connection.createStatement();
            
            StringBuilder sql = new StringBuilder( "CREATE TABLE ActToPay (\nid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY\n");
            ActivityPayOptionLink dummy = new ActivityPayOptionLink();
            String[] fieldTypes = dummy.getFieldsType();
            String[] fieldNames = dummy.getFieldsName();

            for(int i = 0 ; i < fieldNames.length ; i++){
                sql.append(","+fieldNames[i] + " " + fieldTypes[i] +"\n");
            }
            sql.append(")"); 

            System.out.println(sql.toString()+"\n");
            stmt.executeUpdate(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches the database for user with specified credentials
     * @param user the username of the user
     * @param pass the password of the user
     * @return User object of the searched user or null if not found
     */
    public synchronized static User findUser(String user,String pass){

        User findUser;

        if(user == null || pass == null)
            return null;

        try {
            stmt = connection.createStatement();

            String sql = "SELECT * FROM Users WHERE username = '"+user+"' AND password = '"+pass+"'";
            System.out.println(sql.toString()+"\n");
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                if (user.equals(rs.getString("username")) && pass.equals(rs.getString("password"))){
                    
                    User dummy = new User();
                    String[] fieldNames = dummy.getFieldsName();
                    findUser = new User(rs.getString(fieldNames[0]),
                                        Position.valueOf(rs.getString(fieldNames[1])),
                                        rs.getString(fieldNames[2]),
                                        Seniority.valueOf(rs.getString(fieldNames[3])),
                                        rs.getDouble(fieldNames[4]),
                                        rs.getInt(fieldNames[5]),
                                        AccountType.valueOf(rs.getString(fieldNames[6])));
                    findUser.setUsername(rs.getString(fieldNames[7]));
                    findUser.setPasswordSHA(rs.getString(fieldNames[8]));
                    return findUser;
                }
            }

            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    /**
     * Searches the database for user with specified credentials
     * @param user the username of the user
     * @param pass the password of the user
     * @return User object of the searched user or null if not found
     */
    public synchronized static User getUser(String user){

        User findUser;

        if(user == null)
            return null;

        try {
            stmt = connection.createStatement();

            String sql = "SELECT * FROM Users WHERE username = '" + user + "'";
            System.out.println(sql.toString()+"\n");
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                User dummy = new User();
                String[] fieldNames = dummy.getFieldsName();
                findUser = new User(rs.getString(fieldNames[0]),
                                    Position.valueOf(rs.getString(fieldNames[1])),
                                    rs.getString(fieldNames[2]),
                                    Seniority.valueOf(rs.getString(fieldNames[3])),
                                    rs.getDouble(fieldNames[4]),
                                    rs.getInt(fieldNames[5]),
                                    AccountType.valueOf(rs.getString(fieldNames[6])));
                findUser.setUsername(rs.getString(fieldNames[7]));
                return findUser;
            }
            

            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    


    /**
     * Inserts user specified by newUser into the db
     * @param newUser the user to store
     * @return Status of operation succeeded
     */
    public synchronized static boolean insertUserIntoTable(User newUser){
        try {
            stmt = connection.createStatement();
            String[] fieldNames = newUser.getFieldsName();
            StringBuilder sql = new StringBuilder("INSERT INTO Users (");

            for(int i = 0 ;i < fieldNames.length -1 ; i++){
                sql.append(fieldNames[i] + ",");
            }
            sql.append(fieldNames[fieldNames.length-1] + ") VALUES (");
            String[] fields = newUser.getFieldsData();
            for (int i = 0; i < fields.length - 1; i++) {
                sql.append(fields[i] + ",");
            }
            sql.append(fields[fields.length - 1] + ")");
            System.out.println(sql.toString()+"\n");
            stmt.executeUpdate(sql.toString());
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Checks if a username is already in the db
     * @param username username to search for
     * @return if it is used or not
     */
    public synchronized static boolean isUsernameUsed(String username){

        try {
            stmt = connection.createStatement();

            String sql = "SELECT * FROM Users WHERE username='"+username+"'";  
            System.out.println(sql.toString()+"\n");
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return true;
        }

        return false;
    }

    public synchronized static int findUserID(User user){
        try {
            stmt = connection.createStatement();

            String sql = "SELECT id FROM Users WHERE username='"+user.getUsername()+"'"; 
            System.out.println(sql.toString()+"\n");
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
                return rs.getInt("id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        return 0;
    }





    /**
     * Inserts pay option specified by newPayOption into the db
     * @param newPayOption the pay option to store
     * @return Status of operation succeeded
     */
    public synchronized static boolean insertPayOptionIntoTable(PayOption newPayOption){
        try {
            stmt = connection.createStatement();
            String[] fieldNames = newPayOption.getFieldsName();
            StringBuilder sql = new StringBuilder("INSERT INTO PayOptions (");

            for(int i = 0 ;i < fieldNames.length -1 ; i++){
                sql.append(fieldNames[i] + ",");
            }
            sql.append(fieldNames[fieldNames.length-1] + ") VALUES (");
            String[] fields = newPayOption.getFieldsData();
            for (int i = 0; i < fields.length - 1; i++) {
                sql.append(fields[i] + ",");
            }
            sql.append(fields[fields.length - 1] + ")");
            System.out.println(sql.toString()+"\n");
            stmt.executeUpdate(sql.toString());
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    public synchronized static ArrayList<PayOption> getPayoptions(){
        ArrayList<PayOption> retList = new ArrayList<>();

        try {
            stmt = connection.createStatement();

            String sql = "SELECT * FROM PayOptions";     
            System.out.println(sql.toString()+"\n");
            ResultSet rs = stmt.executeQuery(sql);  

            while(rs.next()){
                PayOption tmp = new PayOption(rs.getString("name"),rs.getDouble("percentage"),rs.getString("basis"));
                tmp.setId(rs.getInt("id"));
                retList.add(tmp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return retList;
    }

    

    /**
     * Returns activities ID
     * @param act
     * @return
     */
    private synchronized static int findActivityID(ActivityInformation act){

        try {
            stmt = connection.createStatement();

            String sql = "SELECT id FROM Activities WHERE linkedUser="+act.getFieldsData()[0] + " AND month="+act.getFieldsData()[1];
            System.out.println(sql.toString()+"\n");
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }

        return 0;
        
    }


    /**
     * 
     * @param newAct
     * @return
     */
    public synchronized static boolean insertActivityInformation(ActivityInformation newAct){

        if(newAct == null)
            return false;
        

        int actID = findActivityID(newAct);
        if(actID == 0){
            try {
                stmt = connection.createStatement();
                String[] fieldNames = newAct.getFieldsName();
                StringBuilder sql = new StringBuilder("INSERT INTO Activities (");

                for(int i = 0 ;i < fieldNames.length -1 ; i++){
                    sql.append(fieldNames[i] + ",");
                }
                sql.append(fieldNames[fieldNames.length-1] + ") VALUES (");
                String[] fields = newAct.getFieldsData();
                for (int i = 0; i < fields.length - 1; i++) {
                    sql.append(fields[i] + ",");
                }
                sql.append(fields[fields.length - 1] + ")");
                System.out.println(sql.toString()+"\n");
                stmt.executeUpdate(sql.toString());
                actID = findActivityID(newAct);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }else{
            try {
                stmt = connection.createStatement();
                String[] fieldNames = newAct.getFieldsName();
                String[] fields = newAct.getFieldsData();
                StringBuilder sql = new StringBuilder("UPDATE Activities SET ");

                for(int i = 0 ;i < fieldNames.length -1; i++){
                    sql.append(fieldNames[i] + "=" + fields[i] + ",");
                }
                sql.append(fieldNames[fields.length - 1] + "=" + fields[fields.length - 1]+ " WHERE id = " + actID);
                System.out.println(sql.toString()+"\n");
                stmt.executeUpdate(sql.toString());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }

        deleteLinks(actID);

        for(int j = 0 ; j < newAct.getOptionCount() ; j++){

            ActivityPayOptionLink link = new ActivityPayOptionLink(actID,newAct.getOption(j).getId() ,newAct.getHours(j));

            try {
                stmt = connection.createStatement();
                String[] fieldNames = link.getFieldsName();
                StringBuilder sql = new StringBuilder("INSERT INTO ActToPay (");

                for(int i = 0 ;i < fieldNames.length -1 ; i++){
                    sql.append(fieldNames[i] + ",");
                }
                sql.append(fieldNames[fieldNames.length-1] + ") VALUES (");
                String[] fields = link.getFieldsData();
                for (int i = 0; i < fields.length - 1; i++) {
                    sql.append(fields[i] + ",");
                }
                sql.append(fields[fields.length - 1] + ")");
                System.out.println(sql.toString()+"\n");
                stmt.executeUpdate(sql.toString());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }

        }
        return true;

    }


    /**
     * Searches for an activity for a user 
     * @param user
     * @param month
     * @return
     */
    public synchronized static ActivityInformation findActivityInformation(User user, MonthType month){
        
        ActivityInformation findActivityInformation = null;

        if(user == null || month == null)
            return null;

        try {
            stmt = connection.createStatement();

            String sql = "SELECT B.linkedUser,B.month,B.status,C.id,C.name,C.percentage,C.basis,D.hoursBooked\n"+
                         "FROM Users A,Activities B,PayOptions C, ActToPay D\n"+
                         "WHERE A.id = B.linkedUser AND D.activity = B.id AND D.payoption = C.id AND A.username = '" + user.getUsername()+"' AND B.month = '"+month.getStringRepresentation()+"'";
                         
            System.out.println(sql.toString()+"\n");
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if(findActivityInformation == null){
                    findActivityInformation = new ActivityInformation(MonthType.valueOf(rs.getString("month")));
                    findActivityInformation.setStatus(ActivityStatus.valueOf(rs.getString("status")));
                }
                PayOption tmpopt = new PayOption(rs.getString("name"), rs.getDouble("percentage"),rs.getString("basis"));
                tmpopt.setId(rs.getInt("id"));
                findActivityInformation.addNewPayOption(tmpopt, rs.getInt("hoursBooked"));
            }
            
            return findActivityInformation;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }



    private synchronized static void deleteLinks(int actID){
        
        try {
            stmt = connection.createStatement();

            String sql = "DELETE FROM ActToPay WHERE activity = " +actID;
            System.out.println(sql.toString()+"\n");
            stmt.executeUpdate(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    

}