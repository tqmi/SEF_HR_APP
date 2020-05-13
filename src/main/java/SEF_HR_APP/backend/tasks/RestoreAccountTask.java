package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.interfaces.DeleteAccountInterface;
import javafx.concurrent.Task;

public class RestoreAccountTask extends Task<Boolean> {


    private DeleteAccountInterface data;
    


    @Override
    protected Boolean call() throws Exception {
        int userID = DBHandler.findUserID(data.getUsername());
        if(userID == 0) return false;
        return DBHandler.updateRestoreUser(userID);
    }

    /**
     * @param data
     */
    public RestoreAccountTask(DeleteAccountInterface data) {
        this.data = data;
    }
    
}