package SEF_HR_APP.backend.datamodels.activity;

import java.util.ArrayList;

import SEF_HR_APP.backend.database.DBEntry;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;

public class ActivityInformation implements DBEntry{


    private MonthType month;
    private ArrayList<PayOption> options;
    private ArrayList<Integer> bookedHours;
    private ActivityStatus status;

    private static final String[] fieldNames = {"linkedUser","month","status"};
    private String[] fieldValues;
    private static final String[] fieldType = {"INTEGER CONSTRAINT user_id REFERENCES Users(id) ON DELETE CASCADE","VARCHAR(10)","VARCHAR(10)"};

    /**
    * @param month
    */
    public ActivityInformation(MonthType month) {
        this.month = month;
        status = ActivityStatus.PENDING;
        options = new ArrayList<>();
        bookedHours = new ArrayList<>();
        fieldValues = new String[3];
        fieldValues[1] = "'"+month.getStringRepresentation()+"'";
        fieldValues[2] = "'"+status.getStringRepresentation()+"'";
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

    /**
     * @return the status
     */
    public ActivityStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ActivityStatus status) {
        this.status = status;
        fieldValues[2] = "'"+status.getStringRepresentation()+"'";
    }

    /**
     * @return the month
     */
    public MonthType getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(MonthType month) {
        this.month = month;
    }
}