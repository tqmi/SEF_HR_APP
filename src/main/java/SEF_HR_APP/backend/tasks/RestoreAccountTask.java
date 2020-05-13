package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.interfaces.DeleteAccountInterface;
import javafx.concurrent.Task;

public class RestoreAccountTask extends Task<Boolean> {


    private DeleteAccountInterface data;
    


    @Override
    protected Boolean call() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param data
     */
    public RestoreAccountTask(DeleteAccountInterface data) {
        this.data = data;
    }
    
}