package SEF_HR_APP.backend.datamodels.user;

public enum Position {
    
    ADMIN("ADMIN"),
    CEO("CEO"),
    DEPARTMENT_MANAGER("DEPARTMENT_MANAGER"),
    PROJECT_MANAGER("PROJECT_MANAGER"),
    EMPLOYEE("EMPLOYEE"),
    INTERN("INTERN"),
    VOLUNTEER("VOLUNTEER");

    private String representation;

    private Position(String rep){
        representation = rep;
    }

    public String getStringRepresentation(){
        return representation;
    }
}