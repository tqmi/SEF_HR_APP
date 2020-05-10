package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.tasks.RetrieveActivityTask;
import SEF_HR_APP.interfaces.RetrieveActivityInfo;
import SEF_HR_APP.interfaces.RetrieveActivityInfoResponse;
import javafx.concurrent.Task;

public class RetrieveActivityService extends ServiceTemplate<RetrieveActivityInfo, RetrieveActivityInfoResponse> {

    private RetrieveActivityInfo actInfo;

    @Override
    public RetrieveActivityInfoResponse getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<RetrieveActivityInfoResponse> createTask() {
        return new RetrieveActivityTask(actInfo);
    }

    @Override
    public void setValues(RetrieveActivityInfo value) {
        this.actInfo = value;

    }
    
}