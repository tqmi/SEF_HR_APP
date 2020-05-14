package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import SEF_HR_APP.backend.tasks.RetrievePayOptionTask;
import javafx.concurrent.Task;

public class RetrievePayOptionService extends ServiceTemplate<String, PayOption> {

    private String data;

    @Override
    public void setValues(String value) {
        data = value;
    }

    @Override
    public PayOption getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<PayOption> createTask() {
        return new RetrievePayOptionTask(data);
    }
    
}