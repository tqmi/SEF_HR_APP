package SEF_HR_APP.interfaces;

import SEF_HR_APP.backend.datamodels.activity.MonthType;
import SEF_HR_APP.backend.datamodels.user.User;

public class RetrieveActivityInfo {

    private User user;
    private MonthType month;

    /**
     * @param user
     * @param month
     */
    public RetrieveActivityInfo(User user, MonthType month) {
        this.user = user;
        this.month = month;
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