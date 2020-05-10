package SEF_HR_APP.backend.datamodels.activity;

import SEF_HR_APP.backend.database.DBEntry;

public class ActivityPayOptionLink implements DBEntry{

    
    private int actID;
    private int optID;
    private int hoursBooked;

    private static final String[] fieldNames = {"activity","payoption","hoursBooked"};
    private String[] fieldValues;
    private static final String[] fieldType = {"INTEGER CONSTRAINT activity_id REFERENCES Activities(id)","INTEGER CONSTRAINT payoption_id REFERENCES PayOptions(id)","INTEGER"};


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

    /**
     * @param actID
     * @param optID
     * @param hoursBooked
     * @param fieldValues
     */
    public ActivityPayOptionLink(int actID, int optID, int hoursBooked) {
        this.actID = actID;
        this.optID = optID;
        this.hoursBooked = hoursBooked;

        fieldValues = new String[3];
        fieldValues[0] = ""+actID;
        fieldValues[1] = ""+optID;
        fieldValues[2] = ""+hoursBooked;
    }

    /**
     * 
     */
    public ActivityPayOptionLink() {
    }
    
    

}