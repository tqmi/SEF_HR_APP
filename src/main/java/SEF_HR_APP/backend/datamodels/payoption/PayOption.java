package SEF_HR_APP.backend.datamodels.payoption;

import SEF_HR_APP.backend.database.DBEntry;

public class PayOption implements DBEntry{


    private static final String[] fieldNames = {"name","percentage","basis","deleteStatus"};
    private String[] fieldValues;
    private static final String[] fieldType = {"VARCHAR(255)  UNIQUE","DECIMAL(5,2)","VARCHAR(255)","SMALLINT"};

    private String name;
    private double percentage;
    private String basis;
    private int id;


    public PayOption() {
    }

    public PayOption(String name,Double percentage,String basis){

        this.name = name;
        this.percentage = percentage;
        this.basis = basis;


        fieldValues = new String[4];

        fieldValues[0] = "'" + name + "'";
        fieldValues[1] = String.valueOf(percentage);
        fieldValues[2] = "'" + basis + "'";
        fieldValues[3] = String.valueOf(0);
    }

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return name;
    }

    public double getPercentage(){
        return percentage;
    }


    public String getBasis(){
        return basis;
    }

    public void setName(String name){
        this.name = name;
        fieldValues[0] = "'" + name + "'";
    }

    public void setDeleteStatus(boolean stat){
        if(stat)
            fieldValues[3] = String.valueOf(1);
        else
            fieldValues[3] = String.valueOf(0);
    }

    public void setPercentage(Double percentage){
        this.percentage = percentage;
        fieldValues[1] = String.valueOf(percentage);
    }

    public void setBasis(String basis){
        this.basis = basis;
        fieldValues[2] = "'" + basis + "'";
    }
    
}