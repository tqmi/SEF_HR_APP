package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.interfaces.RetrieveActivityInfo;
import javafx.concurrent.Task;

public class RetrieveActivityTask extends Task<ActivityInformation> {

    private RetrieveActivityInfo actInfo;
    
    /**
     * @param actInfo
     */
    public RetrieveActivityTask(RetrieveActivityInfo actInfo) {
        this.actInfo = actInfo;
        if(actInfo.getUser() == null){
            actInfo.setUser(User.getCurrentUser());
        }
    }
    

    @Override
    protected ActivityInformation call() throws Exception {
        return null;
    }

    
}