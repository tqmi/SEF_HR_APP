package SEF_HR_APP.frontend.scenes;

import SEF_HR_APP.backend.ServiceHandler;
import SEF_HR_APP.backend.ServiceHandler.ServiceID;
import SEF_HR_APP.frontend.popUpBoxes.AlertBoxLogIn;
import SEF_HR_APP.frontend.popUpBoxes.TEMPConfirmBox;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DeletePayOptionScene extends GridPane {

    private Text scenetitle;
    private Label payopt;
    private TextField paybox;
    private Button delete_button;

    public DeletePayOptionScene()
    {
        super();

        //setting layout alignment for scene items
        this.setAlignment(Pos.TOP_LEFT);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        //simple Title label & pos
        scenetitle = new Text("Please choose the pay option you want to delete.");
        scenetitle.setFont(Font.font("Verdana"));
        this.add(scenetitle, 0, 0, 2, 1);

        payopt = new Label("Pay Option:");
        this.add(payopt, 0, 1);
        paybox = new TextField();
        this.add(paybox, 1, 1);

        delete_button = new Button("Delete");
        this.add(delete_button, 2, 1);

        delete_button.setOnMouseClicked(new DeletePayOptionHandler());
        ServiceHandler.setOnSucceededHandler(ServiceID.DELETEPAYOPTIONSERVICE, new PayDeletionSuccessfulHandler());
        
    }

    //event handlers implementation for deleting a pay option from database
    private class DeletePayOptionHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            
            if(TEMPConfirmBox.display("Alert", "Are you sure you want to delete this option?"))
            {
                ServiceHandler.setValues(ServiceID.DELETEPAYOPTIONSERVICE, paybox.getText());
                ServiceHandler.startService(ServiceID.DELETEPAYOPTIONSERVICE);
                AlertBoxLogIn.display("Alert", "Loading...");
            }
        }

    }

    private class PayDeletionSuccessfulHandler implements EventHandler<Event> {

        @Override
        public void handle(Event event)
        {
            if((boolean) ServiceHandler.getValues(ServiceID.DELETEPAYOPTIONSERVICE)){
                    AlertBoxLogIn.display("Alert", "Option successfully deleted!");
            }
            else{
                AlertBoxLogIn.display("Alert", "Option deletion failed!");
            }
        }
    }

}