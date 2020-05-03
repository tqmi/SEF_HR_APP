package SEF_HR_APP.backend.datamodels.user;

public enum AccountType {
    ADMIN("ADMIN"),
    SUPERVISOR("SUPERVISOR"),
    SUPERVISOR_OPERATOR("SUPERVISOR_OPERATOR"),
    EMPLOYEE("EMPLOYEE"),
    EMPLOYEE_OPERATOR("EMPLOYEE_OPERATOR");
    
    private String representation;

    private AccountType(String rep){
        representation = rep;
    }

    public String getStringRepresentation(){
        return representation;
    }
}