package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import SEF_HR_APP.backend.tasks.UpdatePayOptionTask;
import javafx.concurrent.Task;

public class UpdatePayOptionService extends ServiceTemplate<PayOption, Boolean> {

    private PayOption data;

    @Override
    public void setValues(PayOption value) {
        data = value;
    }

    @Override
    public Boolean getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<Boolean> createTask() {
        return new UpdatePayOptionTask(data);
    }
    
}