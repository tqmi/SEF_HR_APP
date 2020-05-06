package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import SEF_HR_APP.backend.tasks.CreatePayOptionTask;
import javafx.concurrent.Task;

public class CreatePayOptionService extends ServiceTemplate<PayOption, Boolean> {

    private PayOption newPayOption;

    @Override
    public void setValues(PayOption value) {

        newPayOption = value;

    }

    @Override
    public Boolean getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<Boolean> createTask() {
        return new CreatePayOptionTask(newPayOption);
    }
    
}