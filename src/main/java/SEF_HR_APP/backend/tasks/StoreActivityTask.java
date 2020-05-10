package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.datamodels.user.User;
import javafx.concurrent.Task;

public class StoreActivityTask extends Task<Boolean> {

    private ActivityInformation activity;

    /**
     * @param activity
     */
    public StoreActivityTask(ActivityInformation activity) {
        super();
        this.activity = activity;
    }

    @Override
    protected Boolean call() throws Exception {
        if(activity != null)
            activity.setUser(DBHandler.findUserID(User.getCurrentUser()));
        else return false;

        return DBHandler.insertActivityInformation(activity);

    }

    
    
}