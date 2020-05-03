package SEF_HR_APP.backend.datamodels.user;

public enum Position {
    
    CEO("CEO"),
    ADMIN("ADMIN");

    private String representation;

    private Position(String rep){
        representation = rep;
    }

    public String getStringRepresentation(){
        return representation;
    }
}