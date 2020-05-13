package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.interfaces.DeleteAccountInterface;
import javafx.concurrent.Task;

public class DeleteAccountTask extends Task<Boolean> {

    DeleteAccountInterface data;


    @Override
    protected Boolean call() throws Exception {
        int userID = DBHandler.findUserID(data.getUsername());
        return DBHandler.updateDeleteUser(userID);
    }

    /**
     * @param data
     */
    public DeleteAccountTask(DeleteAccountInterface data) {
        super();
        this.data = data;
    }
    
}