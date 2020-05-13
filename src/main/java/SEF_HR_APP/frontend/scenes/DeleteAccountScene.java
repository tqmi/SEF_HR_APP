package SEF_HR_APP.frontend.scenes;

import SEF_HR_APP.backend.ServiceHandler;
import SEF_HR_APP.backend.ServiceHandler.ServiceID;
import SEF_HR_APP.frontend.popUpBoxes.AlertBoxLogIn;
import SEF_HR_APP.frontend.popUpBoxes.TEMPConfirmBox;
import SEF_HR_APP.interfaces.DeleteAccountInterface;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class DeleteAccountScene extends GridPane {

    private Label username;
    private TextField userbox;
    private Button delete_button;
    private Button restore_button;

    public DeleteAccountScene() 
    {
        super();

        //setting layout alignment for scene items
        this.setAlignment(Pos.TOP_LEFT);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        //account user name
        username = new Label("Enter username:");
        this.add(username, 0, 0);

        userbox = new TextField();
        this.add(userbox, 1, 0);

        //instance for account deletion button
        delete_button = new Button("Delete");
        this.add(delete_button, 1, 1);

        delete_button.setOnMouseClicked(new DeleteAccountHandler());
        ServiceHandler.setOnSucceededHandler(ServiceID.DELETEACCOUNTSERVICE, new AccDeletionSuccessfulHandler() );

        //instance for account restoration button
        restore_button = new Button("Restore");
        this.add(restore_button, 2, 1);

        restore_button.setOnMouseClicked(new RestoreAccountHandler());
        ServiceHandler.setOnSucceededHandler(ServiceID.RESTOREACCOUNTSERVICE, new AccRestoredSuccessfulHandler() );
    }

    //event handlers implementation for deleting an account from database
    private class DeleteAccountHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            
            if(TEMPConfirmBox.display("Attention", "Are you sure you want to delete this account?"))
            {
                ServiceHandler.setValues(ServiceID.DELETEACCOUNTSERVICE, new DeleteAccountInterface(userbox.getText()));
                ServiceHandler.startService(ServiceID.DELETEACCOUNTSERVICE);
                AlertBoxLogIn.display("Alert", "Loading...");
            }
        }

    }

    private class AccDeletionSuccessfulHandler implements EventHandler<Event> {

        @Override
        public void handle(Event event)
        {
            if((boolean) ServiceHandler.getValues(ServiceID.DELETEACCOUNTSERVICE)){
                    AlertBoxLogIn.display("Alert", "Account successfully deleted!");
            }
            else{
                AlertBoxLogIn.display("Alert", "Account deletion failed!");
            }
        }
    }

    //event handlers implementation for restoring an account back into database
    private class RestoreAccountHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            
            ServiceHandler.setValues(ServiceID.RESTOREACCOUNTSERVICE, new DeleteAccountInterface(userbox.getText()));
            ServiceHandler.startService(ServiceID.RESTOREACCOUNTSERVICE);
            AlertBoxLogIn.display("Alert", "Loading...");
        }

    }

    private class AccRestoredSuccessfulHandler implements EventHandler<Event> {

        @Override
        public void handle(Event event)
        {
            if((boolean) ServiceHandler.getValues(ServiceID.RESTOREACCOUNTSERVICE)){
                AlertBoxLogIn.display("Alert", "Account successfully restored!");
            }
            else{
                AlertBoxLogIn.display("Alert", "Account restoring failed!");
            }
        }
    }
    
}