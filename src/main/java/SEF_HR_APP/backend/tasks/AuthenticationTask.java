package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.interfaces.LoginInfo;
import javafx.concurrent.Task;

public class AuthenticationTask extends Task<Boolean> {

    private LoginInfo info;

    public AuthenticationTask(LoginInfo info){
        super();
        this.info = info;
    }

    public boolean authenticate(){

        User logincred = DBHandler.findUser(info.getUsername(), info.getPassword());

        if(logincred != null){
            logincred.setCurrentUser();
            return true;
        }
        return false;
    }

    @Override
    protected Boolean call() throws Exception {
        Thread.sleep(500);
        return authenticate();
    }

}