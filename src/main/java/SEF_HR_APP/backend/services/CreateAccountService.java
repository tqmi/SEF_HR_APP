package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.backend.tasks.CreateAccountTask;
import javafx.concurrent.Task;

public class CreateAccountService extends ServiceTemplate<User, Boolean> {

    private User newUser;

    @Override
    public void setValues(User value) {
        newUser = value;
    }

    @Override
    public Boolean getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<Boolean> createTask() {
        return new CreateAccountTask(newUser);
    }
    
}