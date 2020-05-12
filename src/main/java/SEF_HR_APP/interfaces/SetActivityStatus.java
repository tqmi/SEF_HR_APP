package SEF_HR_APP.interfaces;

import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.datamodels.user.User;

public class SetActivityStatus {

    private ActivityInformation activityInformation;
    private User user;

    /**
     * @param activityInformation
     * @param user
     */
    public SetActivityStatus(ActivityInformation activityInformation, User user) {
        this.activityInformation = activityInformation;
        this.user = user;
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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
}