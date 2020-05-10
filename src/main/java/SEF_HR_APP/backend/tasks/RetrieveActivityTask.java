package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.interfaces.RetrieveActivityInfo;
import SEF_HR_APP.interfaces.RetrieveActivityInfoResponse;
import javafx.concurrent.Task;

public class RetrieveActivityTask extends Task<RetrieveActivityInfoResponse> {

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
    protected RetrieveActivityInfoResponse call() throws Exception {
        
        RetrieveActivityInfoResponse response = new RetrieveActivityInfoResponse();

        response.setActivityInformation(DBHandler.findActivityInformation(actInfo.getUser(), actInfo.getMonth()));
        response.setOptions(DBHandler.getPayoptions());


        return response;
    }

    
}