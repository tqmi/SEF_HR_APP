package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import javafx.concurrent.Task;

public class UpdatePayOptionTask extends Task<Boolean> {

    private PayOption data;
    

    @Override
    protected Boolean call() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param data
     */
    public UpdatePayOptionTask(PayOption data) {
        this.data = data;
    }
    
}