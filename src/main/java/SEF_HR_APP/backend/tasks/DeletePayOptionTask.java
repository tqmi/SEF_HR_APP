package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import javafx.concurrent.Task;

public class DeletePayOptionTask extends Task<Boolean> {

    private String data;

    @Override
    protected Boolean call() throws Exception {
        return DBHandler.setPayOptionDeleteStatus(data);
    }

    /**
     * @param data
     */
    public DeletePayOptionTask(String data) {
        this.data = data;
    }
    
}