package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.tasks.AuthenticationTask;
import SEF_HR_APP.interfaces.LoginInfo;

public class AuthenticationService extends ServiceTemplate<LoginInfo,Boolean> {

    private LoginInfo info;

    public AuthenticationService(){
    }

    @Override
    protected AuthenticationTask createTask() {

        return new AuthenticationTask(info);
    }

    @Override
    public void setValues(LoginInfo value) {

        info = value;

    }

    @Override
    public Boolean getResponse() {
        return this.getValue();
    }


}