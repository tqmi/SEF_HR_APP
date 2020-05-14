package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import javafx.concurrent.Task;

public class RetrievePayOptionTask extends Task<PayOption> {

    private String data;

    @Override
    protected PayOption call() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param data
     */
    public RetrievePayOptionTask(String data) {
        this.data = data;
    }
    
}