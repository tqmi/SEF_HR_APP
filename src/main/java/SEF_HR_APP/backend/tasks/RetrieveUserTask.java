package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.user.AccountType;
import SEF_HR_APP.backend.datamodels.user.User;
import javafx.concurrent.Task;

public class RetrieveUserTask extends Task<User> {

    private String username;


    @Override
    protected User call() throws Exception {

        if(User.getCurrentUser().getaccountType() != AccountType.EMPLOYEE_OPERATOR && User.getCurrentUser().getaccountType() != AccountType.SUPERVISOR_OPERATOR && User.getCurrentUser().getaccountType() != AccountType.SUPERVISOR)
            return null;


        return DBHandler.getUser(username);
    }

    /**
     * @param username
     */
    public RetrieveUserTask(String username) {
        this.username = username;
    }
    
}