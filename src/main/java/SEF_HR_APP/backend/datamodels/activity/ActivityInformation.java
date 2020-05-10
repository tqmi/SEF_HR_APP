package SEF_HR_APP.backend.datamodels.activity;

import java.util.ArrayList;

import SEF_HR_APP.backend.database.DBEntry;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;

public class ActivityInformation implements DBEntry{


    private MonthType month;
    private ArrayList<PayOption> options;
    private ArrayList<Integer> bookedHours;

    private static final String[] fieldNames = {"linkedUser","month"};
    private String[] fieldValues;
    private static final String[] fieldType = {"INTEGER CONSTRAINT user_id REFERENCES Users(id)","VARCHAR(10)"};

    /**
    * @param month
    */
    public ActivityInformation(MonthType month) {
        this.month = month;
        options = new ArrayList<>();
        bookedHours = new ArrayList<>();
        fieldValues = new String[2];
        fieldValues[1] = "'"+month.getStringRepresentation()+"'";
    }

    /**
     * 
     */
    public ActivityInformation() {
    }
    

    public int getOptionCount(){
        return options.size();
    }

    public PayOption getOption(int index){
        return options.get(index);
    }

    public int getHours(int index){
        return bookedHours.get(index);
    }

    public void addNewPayOption(PayOption option, Integer hours){
        options.add(option);
        bookedHours.add(hours);  
    }

    public void clearOptions(){
        options.clear();
        bookedHours.clear();
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

    
    public void setUser(int id){
        fieldValues[0] = ""+id;
    }
}