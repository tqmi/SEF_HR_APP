package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.tasks.RetrieveActivityTask;
import SEF_HR_APP.interfaces.RetrieveActivityInfo;
import javafx.concurrent.Task;

public class RetrieveActivityService extends ServiceTemplate<RetrieveActivityInfo, ActivityInformation> {

    private RetrieveActivityInfo actInfo;

    @Override
    public ActivityInformation getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<ActivityInformation> createTask() {
        return new RetrieveActivityTask(actInfo);
    }

    @Override
    public void setValues(RetrieveActivityInfo value) {
        this.actInfo = value;

    }
    
}