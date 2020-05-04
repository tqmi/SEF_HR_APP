package SEF_HR_APP.frontend.scenes;

import SEF_HR_APP.frontend.popUpBoxes.AlertBoxLogIn;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class CreateAccountScene extends GridPane {

    private Text scenetitle;

    private Label name;
    private TextField namebox;

    private Label position;
    private ComboBox posbox;

    private Label email;
    private TextField emailbox;

    private Label seniority;
    private ComboBox senbox;

    private Label salary;
    private TextField salarybox;

    private Label leaveDays;
    private TextField leaveDaysbox;

    private Label accType;
    private ComboBox accTypebox;

    private Button create_button;


    public CreateAccountScene()
    {
        super();

        this.setAlignment(Pos.CENTER_LEFT);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));

        //simple Title label & pos
		scenetitle = new Text("Please fill in the following form.");
        this.add(scenetitle, 0, 0, 2, 1);
        
        //full name 
        name = new Label("Enter employee full name:");
        this.add(name, 0, 1);
		namebox = new TextField();	
        this.add(namebox, 1, 1);       

        //work position
        position = new Label("Choose employee position:");
        this.add(position, 0, 2);
        posbox = new ComboBox();
        posbox.getItems().addAll("Option 1", "Option 2", "Option 3");
        this.add(posbox, 1, 2);

        //work email
        email = new Label("Enter employee email:");
        this.add(email, 0, 3);
        emailbox = new TextField();
        this.add(emailbox, 1, 3);

        //seniority in work domain
        seniority = new Label("Choose employee seniority:");
        this.add(seniority, 0, 4);
        senbox = new ComboBox();
        senbox.getItems().addAll("Option 1", "Option 2", "Option 3");
        this.add(senbox, 1, 4);

        //base salary
        salary = new Label("Enter employee base salary:");
        this.add(salary, 0, 5);
        salarybox = new TextField();
        this.add(salarybox, 1, 5);
        
        //yearly leave days
        leaveDays = new Label("Enter yearly leave days:");
        this.add(leaveDays, 0, 6);
        leaveDaysbox = new TextField();
        this.add(leaveDaysbox, 1, 6);

        //account type
        accType = new Label("Choose account type:");
        this.add(accType, 0, 7);
        accTypebox = new ComboBox();
        accTypebox.getItems().addAll("Option 1", "Option 2", "Option 3");
        this.add(accTypebox, 1, 7);

        //validate entries and create account
        create_button = new Button("Create");
        this.add(create_button, 0, 8);

        create_button.setOnMouseClicked(new testAlertforCreateAccount());

    }

    private class testAlertforCreateAccount implements EventHandler<Event> {

        @Override
        public void handle(Event e)
        {
            AlertBoxLogIn.display("Account Creation Alert", "Create option accessed");
        }
    }
    
}