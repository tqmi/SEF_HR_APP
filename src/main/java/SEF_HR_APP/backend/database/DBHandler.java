package SEF_HR_APP.backend.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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

    private synchronized static void createDB() throws SQLException {
        connection = DriverManager.getConnection(dbURL + "create=true;");
        System.out.println("DB created! Adding User table ...");
        createUserTable();
        System.out.println("User table added! Adding admin account ...");
        addAdminAccountToTable();
    }

    private synchronized static void addAdminAccountToTable() {

        readAdminAccountDetails();

        try {
            stmt = connection.createStatement();
            User admin = new User("admin", Position.ADMIN, "admin", Seniority.JUNIOR, 0, 0, AccountType.ADMIN, adminusername,
                    adminpassword);
            StringBuilder sql = new StringBuilder("INSERT INTO Users VALUES (1,");
            String[] fields = admin.getFieldsData();
            for (int i = 0; i < fields.length - 1; i++) {
                sql.append(fields[i] + ",");
            }
            sql.append(fields[fields.length - 1] + ")");
            stmt.executeUpdate(sql.toString());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ;
        }

    }

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

    private synchronized static void adminUseDefaults(){
        adminusername = "admin";
        adminpassword = "admin";
    }

    private synchronized static void createUserTable() {
        try {
            stmt = connection.createStatement();
        
        StringBuilder sql = new StringBuilder( "CREATE TABLE Users (\nid INTEGER not NULL,\n");
        User dummy = new User();
        String[] fieldTypes = dummy.getFieldsType();
        String[] fieldNames = dummy.getFieldsName();

        for(int i = 0 ; i < fieldNames.length ; i++){
            sql.append(fieldNames[i] + " " + fieldTypes[i] +",\n");
        }
        sql.append(" PRIMARY KEY ( id ))"); 

        System.out.println(sql.toString());
        stmt.executeUpdate(sql.toString());
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }

    public synchronized static User findUser(String user,String pass){

        User findUser;

        if(user == null || pass == null)
            return null;

        try {
            stmt = connection.createStatement();

            String sql = "SELECT * FROM Users";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (user.equals(rs.getString("username")) && pass.equals(rs.getString("password"))){
                    
                    User dummy = new User();
                    String[] fieldNames = dummy.getFieldsName();
                    findUser = new User(rs.getString(fieldNames[0]),
                                        Position.valueOf(rs.getString(fieldNames[1])),
                                        rs.getString(fieldNames[2]),
                                        Seniority.valueOf(rs.getString(fieldNames[3])),
                                        rs.getDouble(fieldNames[4]),
                                        rs.getInt(fieldNames[5]),
                                        AccountType.valueOf(rs.getString(fieldNames[6])),
                                        rs.getString(fieldNames[7]),
                                        rs.getString(fieldNames[8]));
                    return findUser;
                }
            }

            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public  synchronized static void close(){
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

}