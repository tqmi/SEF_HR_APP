package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import javafx.concurrent.Task;

public class CreatePayOptionTask extends Task<Boolean> {


    private PayOption newPayOption;

    public CreatePayOptionTask(PayOption newPayOption){
        super();
        this.newPayOption = newPayOption;
    }


    @Override
    protected Boolean call() throws Exception {
        return DBHandler.insertPayOptionIntoTable(newPayOption);
    }
    
}