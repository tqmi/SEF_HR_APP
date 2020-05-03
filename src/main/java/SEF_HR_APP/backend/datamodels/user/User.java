package SEF_HR_APP.backend.datamodels.user;

import SEF_HR_APP.backend.database.DBEntry;

public class User implements DBEntry{

    private static User currentUser;

    private String name;
    private Position position;
    private String email;
    private Seniority seniority;
    private double salary;
    private int leaveDays;
    private AccountType accountType;
    private String username;
    private String password;
    private static final String[] fieldNames = {"name","position","email","seniority","salary","leaveDays","accountType","username","password"};
    private String[] fieldValues;
    private static final String[] fieldType = {"VARCHAR(255)","VARCHAR(255)","VARCHAR(255)","VARCHAR(255)","DECIMAL(10,3)","INTEGER","VARCHAR(255)","VARCHAR(255)","VARCHAR(255)"};


    

    /**
     * @param name
     * @param position
     * @param email
     * @param seniority
     * @param salary
     * @param leaveDays
     * @param accountType
     * @param username
     * @param password
     */
    public User(String name, Position position, String email, Seniority seniority, double salary, int leaveDays,
            AccountType accountType, String username, String password) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.seniority = seniority;
        this.salary = salary;
        this.leaveDays = leaveDays;
        this.accountType = accountType;
        this.username = username;
        this.password = password;
        fieldValues = new String[9];
            
        fieldValues[0] = "'" + name + "'";
        fieldValues[1] = "'" + position.getStringRepresentation() + "'";
        fieldValues[2] = "'"+email+"'";
        fieldValues[3] = "'"+seniority.getStringRepresentation()+"'";
        fieldValues[4] = String.valueOf(salary);
        fieldValues[5] = String.valueOf(leaveDays);
        fieldValues[6] = "'"+accountType.getStringRepresentation()+"'";
        fieldValues[7] = "'"+username+"'";
        fieldValues[8] = "'"+password+"'";
    }

    public User(){
    }

    @Override
    public String[] getFieldsName() {
        return fieldNames;
    }

    @Override
    public String[] getFieldsData() {
        return fieldValues;
    }

    @Override
    public String[] getFieldsType() {
        return fieldType;
    }

    public void setCurrentUser(){
        currentUser = this;
    }   

    public static void resetCurrentUser(){
        currentUser = null;
    }

    public static User getCurrentUser(){
        return currentUser;
    }
    

}