package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import javafx.concurrent.Task;

public class UpdatePayOptionTask extends Task<Boolean> {

    private PayOption data;
    

    @Override
    protected Boolean call() throws Exception {
        if(data == null)    
            return false;
        return DBHandler.updatePayOption(data);
    }

    /**
     * @param data
     */
    public UpdatePayOptionTask(PayOption data) {
        this.data = data;
    }
    
}