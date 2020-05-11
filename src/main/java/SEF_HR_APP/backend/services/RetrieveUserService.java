package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.backend.tasks.RetrieveUserTask;
import javafx.concurrent.Task;

public class RetrieveUserService extends ServiceTemplate<String, User> {

    private String username;


    @Override
    public void setValues(String value) {
        username = value;
    }

    @Override
    public User getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<User> createTask() {
        return new RetrieveUserTask(username);
    }

    
}