package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.tasks.UpdateReviewStatusTask;
import SEF_HR_APP.interfaces.SetActivityStatus;
import javafx.concurrent.Task;

public class UpdateReviewStatusService extends ServiceTemplate<SetActivityStatus, Boolean> {

    private SetActivityStatus stat;

    @Override
    public void setValues(SetActivityStatus value) {
        stat = value;
    }

    @Override
    public Boolean getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<Boolean> createTask() {
        return new UpdateReviewStatusTask(stat);
    }

   
    
}