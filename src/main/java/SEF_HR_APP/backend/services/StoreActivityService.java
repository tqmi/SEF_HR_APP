package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.tasks.StoreActivityTask;
import javafx.concurrent.Task;

public class StoreActivityService extends ServiceTemplate<ActivityInformation,Boolean> {

    private ActivityInformation activity;

    @Override
    public void setValues(ActivityInformation value) {
        this.activity = value;
    }

    @Override
    public Boolean getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<Boolean> createTask() {
        return new StoreActivityTask(activity);
    }


    
}