package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.tasks.DeletePayOptionTask;
import javafx.concurrent.Task;

public class DeletePayOptionService extends ServiceTemplate<String, Boolean> {


    private String data;

    @Override
    public void setValues(String value) {
        data = value;
    }

    @Override
    public Boolean getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<Boolean> createTask() {
        return new DeletePayOptionTask(data);
    }
    
}