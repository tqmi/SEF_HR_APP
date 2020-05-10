package SEF_HR_APP.backend.datamodels.activity;

import java.util.Calendar;

public enum MonthType {
    JAN("JAN",0),
    FEB("FEB",1),
    MAR("MAR",2),
    APR("APR",3),
    MAY("MAY",4),
    IUN("IUN",5),
    IUL("IUL",6),
    AUG("AUG",7),
    SEP("SEP",8),
    OCT("OCT",9),
    NOV("NOV",10),
    DEC("DEC",11);
    

    private String representation;
    private int index;


    private MonthType(String rep,int index){
        representation = rep;
        this.index = index;
    }

    public String getStringRepresentation(){
        return representation;
    }

    public int monthDifferenceToCurrent(){
        
        int other = Calendar.getInstance().get(Calendar.MONTH);
        
        int res = other - index;

        if(res < 0)
            res += 12;

        return res;
    }
    
}