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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ModifyPayOptionScene extends GridPane {

    private Text scenetitle;
    private Label payopt;
    private TextField paybox;
    private Button load_button;

    private Text scenetitle1;
    private Label name;
    private TextField namebox;
    private Label percentage;
    private TextField perbox;
    private Text perc;
    private Label legalBasis;
    private TextField legbox;
    private Button save_button;

    private PayOption selopt;

    public ModifyPayOptionScene()
    {
        super();

        this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(10);
		this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        scenetitle = new Text("Please choose option you want to modify.");
        scenetitle.setFont(Font.font("Verdana"));
        this.add(scenetitle, 0, 0, 2, 1);

        
        payopt = new Label("Pay option:");
        this.add(payopt, 0, 1);

        paybox = new TextField();
        this.add(paybox, 1, 1);

        load_button = new Button("Load");
        this.add(load_button, 2, 1);

        load_button.setOnMouseClicked(new LoadPayOptionHandler());

        /////
        //simple Title label & pos
        scenetitle1 = new Text("Please modify desired fields regarding pay option information.");
        scenetitle1.setFont(Font.font("Verdana"));
        this.add(scenetitle1, 0, 8, 2, 1);

        //pay option name 
        name = new Label("Type in a new pay option:");
        this.add(name, 0, 9);
		namebox = new TextField();	
        this.add(namebox, 1, 9);       

        //pay percentage 
        percentage = new Label("Enter pay percentage:");
        this.add(percentage, 0, 10);
		perbox = new TextField();	
        this.add(perbox, 1, 10);
        perc = new Text("%");
        this.add(perc, 2, 10);

        //legal basis 
        legalBasis = new Label("Enter legal basis for pay option:");
        this.add(legalBasis, 0, 11);
		legbox = new TextField();	
        this.add(legbox, 1, 11);      

        save_button = new Button("Save");
        this.add(save_button, 0, 12);

        save_button.setOnMouseClicked(new SavePayOptionHandler());

        ServiceHandler.setOnSucceededHandler(ServiceID.RETRIEVEPAYOPTIONSERVICE, new PayOptionLoadedHandler());
        ServiceHandler.setOnSucceededHandler(ServiceID.UPDATEPAYOPTIONSERVICE, new PayOptionSavedHandler());
    }
    
    private class SavePayOptionHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {

            if(selopt == null){
                AlertBoxLogIn.display("Alert!", "Must first load an option!");
                return;
            }

            selopt.setName(namebox.getText());
            selopt.setPercentage(Double.valueOf(perbox.getText()));
            selopt.setBasis(legbox.getText());

            ServiceHandler.setValues(ServiceID.UPDATEPAYOPTIONSERVICE, selopt);
            ServiceHandler.startService(ServiceID.UPDATEPAYOPTIONSERVICE);
            AlertBoxLogIn.display("Alert", "Working...");

        }
    }

    private class PayOptionSavedHandler implements EventHandler<Event>{

        @Override
        public void handle(Event event) {
            if((boolean)ServiceHandler.getValues(ServiceID.UPDATEPAYOPTIONSERVICE)){
                AlertBoxLogIn.display("Alert", "Option updated!");
                return;
            }else{
                AlertBoxLogIn.display("Alert", "Could not uptade option information!");
            }

        }

    }

    private class LoadPayOptionHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            
            ServiceHandler.setValues(ServiceID.RETRIEVEPAYOPTIONSERVICE, paybox.getText());
            ServiceHandler.startService(ServiceID.RETRIEVEPAYOPTIONSERVICE);
            AlertBoxLogIn.display("Alert", "Loading...");
        }
    }

    private class PayOptionLoadedHandler implements EventHandler<Event>{

        @Override
        public void handle(Event event) {

            selopt = ServiceHandler.getValues(ServiceID.RETRIEVEPAYOPTIONSERVICE);

            if(selopt == null){
                AlertBoxLogIn.display("Alert", "Option not found or permission denied!");
                return;
            }

            AlertBoxLogIn.closeWindow();

            namebox.setText(selopt.getName());
            perbox.setText(String.valueOf(selopt.getPercentage()));
            legbox.setText(selopt.getBasis());

        }

    }

}