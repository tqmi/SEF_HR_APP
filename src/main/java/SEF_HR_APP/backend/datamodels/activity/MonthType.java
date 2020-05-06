package SEF_HR_APP.backend.datamodels.activity;

public enum MonthType {
    JAN("JAN"),
    FEB("FEB"),
    MAR("MAR"),
    APR("APR"),
    MAY("MAY"),
    IUN("IUN"),
    IUL("IUL"),
    AUG("AUG"),
    SEP("SEP"),
    OCT("OCT"),
    NOV("NOV"),
    DEC("DEC");
    

    private String representation;

    private MonthType(String rep){
        representation = rep;
    }

    public String getStringRepresentation(){
        return representation;
    }
    
}