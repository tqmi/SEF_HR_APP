package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.backend.tasks.UpdateUserTask;
import javafx.concurrent.Task;

public class UpdateUserService extends ServiceTemplate<User, Boolean> {

    private User user;


    @Override
    public void setValues(User value) {
        user = value;
    }

    @Override
    public Boolean getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<Boolean> createTask() {
        return new UpdateUserTask(user);
    }
    
}