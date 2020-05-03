package SEF_HR_APP.backend.datamodels.user;

public enum Seniority {
    
    JUNIOR("JUNIOR");

    private String representation;

    private Seniority(String rep){
        representation = rep;
    }

    public String getStringRepresentation(){
        return representation;
    }


}