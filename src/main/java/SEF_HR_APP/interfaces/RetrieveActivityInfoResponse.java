package SEF_HR_APP.interfaces;

import java.util.ArrayList;

import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;

public class RetrieveActivityInfoResponse {

    private ActivityInformation activityInformation;
    private ArrayList<PayOption> options;

    /**
     * @param activityInformation
     * @param options
     */
    public RetrieveActivityInfoResponse(ActivityInformation activityInformation, ArrayList<PayOption> options) {
        this.activityInformation = activityInformation;
        this.options = options;
    }

    /**
     * @return the activityInformation
     */
    public ActivityInformation getActivityInformation() {
        return activityInformation;
    }

    /**
     * @param activityInformation the activityInformation to set
     */
    public void setActivityInformation(ActivityInformation activityInformation) {
        this.activityInformation = activityInformation;
    }

    /**
     * @return the options
     */
    public ArrayList<PayOption> getOptions() {
        return options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(ArrayList<PayOption> options) {
        this.options = options;
    }

    /**
     * 
     */
    public RetrieveActivityInfoResponse() {
    }

    

    
}