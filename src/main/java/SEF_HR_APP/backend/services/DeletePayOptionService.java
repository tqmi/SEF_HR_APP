package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import javafx.concurrent.Task;

public class DeletePayOptionService extends ServiceTemplate<String, Boolean> {

    @Override
    public void setValues(String value) {
        // TODO Auto-generated method stub

    }

    @Override
    public Boolean getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<Boolean> createTask() {
        // TODO Auto-generated method stub
        return null;
    }
    
}