package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.backend.mail.MailService;
import SEF_HR_APP.interfaces.SetActivityStatus;
import javafx.concurrent.Task;

public class UpdateReviewStatusTask extends Task<Boolean> {


    private ActivityInformation act;
    private User user;

    public UpdateReviewStatusTask(SetActivityStatus stat){
        act = stat.getActivityInformation();
        user = stat.getUser();
    }


    @Override
    protected Boolean call() throws Exception {
        
        if(user == null || act == null)
            return false;

        Boolean retval = DBHandler.setActivityStatus(act);

        if(retval){
            MailService.sendActivityStatusMessage(user, act);
        }
        

        return true & retval;
    }
    
}