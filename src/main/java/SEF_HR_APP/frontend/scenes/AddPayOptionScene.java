package SEF_HR_APP.frontend.scenes;

import SEF_HR_APP.backend.ServiceHandler;
import SEF_HR_APP.backend.ServiceHandler.ServiceID;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import SEF_HR_APP.frontend.popUpBoxes.AlertBoxLogIn;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AddPayOptionScene extends GridPane {

    private Text scenetitle;

    private Label name;
    private TextField namebox;

    private Label percentage;
    private TextField perbox;

    private Label legalBasis;
    private TextField legbox;

    private Button add_button;

    private Text perc;

    public AddPayOptionScene() 
    {
        super();

        this.setAlignment(Pos.CENTER_LEFT);
		this.setHgap(10);
		this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        
        //simple Title label & pos
        scenetitle = new Text("Please fill in the required information for adding a new pay option.");
        scenetitle.setFont(Font.font("Verdana"));
        this.add(scenetitle, 0, 0, 2, 1);

        //pay option name 
        name = new Label("Type in a new pay option:");
        this.add(name, 0, 1);
		namebox = new TextField();	
        this.add(namebox, 1, 1);       

        //pay percentage 
        percentage = new Label("Enter pay percentage:");
        this.add(percentage, 0, 2);
		perbox = new TextField();	
        this.add(perbox, 1, 2);
        perc = new Text("%");
        this.add(perc, 2, 2);

        //legal basis 
        legalBasis = new Label("Enter legal basis for pay option:");
        this.add(legalBasis, 0, 3);
		legbox = new TextField();	
        this.add(legbox, 1, 3);       

        //validate entries and create account
        add_button = new Button("Add");
        this.add(add_button, 0, 4);

        //call for EventHandler for data validation and account creation 
        add_button.setOnMouseClicked(new AddPayOptionHandler());
        ServiceHandler.setOnSucceededHandler(ServiceID.CREATEPAYOPTIONSERVICE, new PayOptionAddedSuccessfullyHandler() );


    }

    private class AddPayOptionHandler implements EventHandler<Event> {

        @Override
        public void handle(Event e)
        {
            if(namebox.getText().trim().isEmpty() || perbox.getText().trim().isEmpty() || legbox.getText().trim().isEmpty())
                AlertBoxLogIn.display("Alert", "All forms are mandatory to fill");
            else
            {
                ServiceHandler.setValues(ServiceID.CREATEPAYOPTIONSERVICE, new PayOption(namebox.getText(), Double.valueOf(perbox.getText()), legbox.getText()));
                ServiceHandler.startService(ServiceID.CREATEPAYOPTIONSERVICE);
                AlertBoxLogIn.display("Alert", "Working...");
            }
        }
    }

    private class PayOptionAddedSuccessfullyHandler implements EventHandler<Event> {

        @Override
        public void handle(Event e)
        {
            if((boolean) ServiceHandler.getValues(ServiceID.CREATEPAYOPTIONSERVICE)){
                AlertBoxLogIn.display("Alert", "Pay option successfully added!");
            }
            else{
                AlertBoxLogIn.display("Alert", "Pay option creation failed!");
            }
        }
    }
    
}