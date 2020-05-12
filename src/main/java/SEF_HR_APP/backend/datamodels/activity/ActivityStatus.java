package SEF_HR_APP.backend.datamodels.activity;

public enum ActivityStatus {
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");
    
    private String representation;

    private ActivityStatus(String rep){
        representation = rep;
    }

    public String getStringRepresentation(){
        return representation;
    }
}