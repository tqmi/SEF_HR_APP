package SEF_HR_APP.backend.datamodels.user;

public enum Seniority {
    
    JUNIOR("JUNIOR"),
    SENIOR("SENIOR"),
    VOLUNTEER("VOLUNTEER"),
    INTERN("INTERN");

    private String representation;

    private Seniority(String rep){
        representation = rep;
    }

    public String getStringRepresentation(){
        return representation;
    }


}