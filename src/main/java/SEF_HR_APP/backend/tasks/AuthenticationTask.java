package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.interfaces.LoginInfo;
import javafx.concurrent.Task;

public class AuthenticationTask extends Task<Boolean> {

    private LoginInfo info;

    public AuthenticationTask(LoginInfo info){
        this.info = info;

    }

    public boolean authenticate(){

        if(info.getUsername().equals("admin") && info.getPassword().equals("admin"))
            return true;

        return false;
    }

    @Override
    protected Boolean call() throws Exception {
        Thread.sleep(500);
        return authenticate();
    }

}