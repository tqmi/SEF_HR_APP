package SEF_HR_APP.frontend.scenes;

import SEF_HR_APP.backend.ServiceHandler;
import SEF_HR_APP.backend.ServiceHandler.ServiceID;
import SEF_HR_APP.frontend.popUpBoxes.AlertBoxLogIn;
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

    public DeleteAccountScene() 
    {
        super();

        this.setAlignment(Pos.TOP_LEFT);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        username = new Label("Enter username:");
        this.add(username, 0, 0);

        userbox = new TextField();
        this.add(userbox, 1, 0);

        delete_button = new Button("Delete");
        this.add(delete_button, 1, 1);

        delete_button.setOnMouseClicked(new DeleteAccountHandler());
        ServiceHandler.setOnSucceededHandler(ServiceID.DELETEACCOUNTSERVICE, new AccDeletionSuccessfulHandler() );

    }

    private class DeleteAccountHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            
            ServiceHandler.setValues(ServiceID.DELETEACCOUNTSERVICE, new DeleteAccountInterface(userbox.getText()));
            ServiceHandler.startService(ServiceID.DELETEACCOUNTSERVICE);
            AlertBoxLogIn.display("Alert", "Loading...");
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
    
}