package SEF_HR_APP.backend.datamodels.activity;

import SEF_HR_APP.backend.database.DBEntry;

public class ActivityInformation implements DBEntry{


    private static final String[] fieldNames = {"linkedUser","month"};
    private String[] fieldValues;
    private static final String[] fieldType = {"INTEGER CONSTRAINT user_id REFERENCES Users(id)","VARCHAR(10)"};


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