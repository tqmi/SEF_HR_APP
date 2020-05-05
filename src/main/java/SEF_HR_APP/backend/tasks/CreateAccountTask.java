package SEF_HR_APP.backend.tasks;

import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.backend.security.PasswordGenerator;
import javafx.concurrent.Task;

public class CreateAccountTask extends Task<Boolean> {

    private User newUser;
    private String password;
    private String username;

    public CreateAccountTask(User user){
        newUser = user;
    }

    @Override
    protected Boolean call() throws Exception {
        createUsernameAndPassword();
        return sendEmail() && storeUser();
    }

    private void createUsernameAndPassword(){
        String name = newUser.getName();

        name = name.replaceAll("\\s","");

        username = name.substring(0,6).toLowerCase();

        int i=1;
        while(DBHandler.isUsernameUsed(username+i))
            i++;

        username = username + i;

        PasswordGenerator generator = new PasswordGenerator.PasswordGeneratorBuilder()
                                                            .useDigits(true)
                                                            .useLower(true)
                                                            .useUpper(true)
                                                            .usePunctuation(true)
                                                            .build();
    
        password = generator.generate(10);

        newUser.setUsername(username);
        newUser.setPassword(password);

    }

    private boolean sendEmail() {
        System.out.println(username + " " + password);
        return true;
    }

    private boolean storeUser() {
        return  DBHandler.insertUserIntoTable(newUser);
    }
    
}