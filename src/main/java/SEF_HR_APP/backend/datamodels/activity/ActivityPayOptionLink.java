package SEF_HR_APP.backend.datamodels.activity;

import SEF_HR_APP.backend.database.DBEntry;

public class ActivityPayOptionLink implements DBEntry{

    
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
    
}