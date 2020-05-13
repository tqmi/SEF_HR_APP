package SEF_HR_APP.backend.services;

import SEF_HR_APP.backend.ServiceTemplate;
import SEF_HR_APP.backend.tasks.DeleteAccountTask;
import SEF_HR_APP.interfaces.DeleteAccountInterface;
import javafx.concurrent.Task;

public class DeleteAccountService extends ServiceTemplate<DeleteAccountInterface, Boolean> {

    private DeleteAccountInterface data;


    @Override
    public void setValues(DeleteAccountInterface value) {
        data = value;

    }

    @Override
    public Boolean getResponse() {
        return this.getValue();
    }

    @Override
    protected Task<Boolean> createTask() {
        return new DeleteAccountTask(data);
    }
    
}