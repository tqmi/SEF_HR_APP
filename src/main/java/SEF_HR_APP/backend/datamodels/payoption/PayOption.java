package SEF_HR_APP.backend.datamodels.payoption;

import SEF_HR_APP.backend.database.DBEntry;

public class PayOption implements DBEntry{


    private static final String[] fieldNames = {"name","percentage","basis"};
    private String[] fieldValues;
    private static final String[] fieldType = {"VARCHAR(255)  UNIQUE","DECIMAL(5,2)","VARCHAR(255)"};

    private String name;
    private double percentage;
    private String basis;


    public PayOption() {
    }

    public PayOption(String name,Double percentage,String basis){

        this.name = name;
        this.percentage = percentage;
        this.basis = basis;


        fieldValues = new String[3];

        fieldValues[0] = "'" + name + "'";
        fieldValues[1] = String.valueOf(percentage);
        fieldValues[2] = "'" + basis + "'";
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



    
}