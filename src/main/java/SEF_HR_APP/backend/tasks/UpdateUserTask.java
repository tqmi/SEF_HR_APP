package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.user.User;
import javafx.concurrent.Task;

public class UpdateUserTask extends Task<Boolean> {

    private User user;


    @Override
    protected Boolean call() throws Exception {
        if(user == null)
            return false;
        return DBHandler.updateUser(user);
    }

    /**
     * @param user
     */
    public UpdateUserTask(User user) {
        this.user = user;
    }
    
}