package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.user.User;
import javafx.concurrent.Task;

public class CreateAccountTask extends Task<Boolean> {

    private User newUser;

    public CreateAccountTask(User user){
        newUser = user;
    }

    @Override
    protected Boolean call() throws Exception {
        storeUser();
        sendEmail();
        return true;
    }

    private void sendEmail() {
    }

    private void storeUser() {
        DBHandler.insertUserIntoTable(newUser);
    }
    
}