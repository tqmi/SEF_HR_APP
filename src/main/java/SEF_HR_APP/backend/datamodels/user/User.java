package SEF_HR_APP.backend.datamodels.user;

import SEF_HR_APP.backend.database.DBEntry;
import SEF_HR_APP.backend.security.Hasher;

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
    private static final String[] fieldType = {"VARCHAR(255)","VARCHAR(255)","VARCHAR(255)","VARCHAR(255)","DECIMAL(10,3)","INTEGER","VARCHAR(255)","VARCHAR(255) UNIQUE","VARCHAR(255)"};


    

    /**
     * @param name
     * @param position
     * @param email
     * @param seniority
     * @param salary
     * @param leaveDays
     * @param accountType
     */
    public User(String name, Position position, String email, Seniority seniority, double salary, int leaveDays,
            AccountType accountType) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.seniority = seniority;
        this.salary = salary;
        this.leaveDays = leaveDays;
        this.accountType = accountType;
        fieldValues = new String[9];
        setFieldValues();
    }

    private void setFieldValues(){
        fieldValues[0] = "'" + name + "'";
        fieldValues[1] = "'" + position.getStringRepresentation() + "'";
        fieldValues[2] = "'"+email+"'";
        fieldValues[3] = "'"+seniority.getStringRepresentation()+"'";
        fieldValues[4] = String.valueOf(salary);
        fieldValues[5] = String.valueOf(leaveDays);
        fieldValues[6] = "'"+accountType.getStringRepresentation()+"'";
        
        
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

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
        fieldValues[7] = "'"+username+"'";
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = Hasher.getSHA256(username+password);
        fieldValues[8] = "'"+this.password+"'";
    }

    public void setPasswordSHA(String password){
        this.password = password;
        fieldValues[8] = "'"+password+"'";    
    }

      /** 
     * @return accountType
    */
    public AccountType getaccountType() {
        return accountType;
    }

    /**
     * @param accountType
     */
    public void setaccountType() {
        this.accountType = accountType;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    

}