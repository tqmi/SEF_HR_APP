package SEF_HR_APP.frontend.scenes;

import java.util.Arrays;

import org.checkerframework.checker.units.qual.A;

import SEF_HR_APP.backend.ServiceHandler;
import SEF_HR_APP.backend.ServiceHandler.ServiceID;
import SEF_HR_APP.backend.datamodels.user.AccountType;
import SEF_HR_APP.backend.datamodels.user.Position;
import SEF_HR_APP.backend.datamodels.user.Seniority;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.frontend.popUpBoxes.AlertBoxLogIn;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ModifyAccountScene extends GridPane{
    
    private Label username;
    private TextField usernamebox;
    private Button loadUserBtn;

    private Text scenetitle;
    private Text scenetitle1;

    private Label name;
    private TextField namebox;

    private Label position;
    private ComboBox<Position> posbox;

    private Label email;
    private TextField emailbox;

    private Label seniority;
    private ComboBox<Seniority> senbox;

    private Label salary;
    private TextField salarybox;

    private Label leaveDays;
    private TextField leaveDaysbox;

    private Label accType;
    private ComboBox<AccountType> accTypebox;

    private Button saveBtn;

    private User selectedUser;



    public ModifyAccountScene()
    {
        super();

        this.setAlignment(Pos.TOP_LEFT);
		this.setHgap(10);
		this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        
        scenetitle = new Text("Please choose account you want to modify.");
        scenetitle.setFont(Font.font("Verdana"));
        username = new Label("Username : ");
        usernamebox = new TextField();
        loadUserBtn = new Button("Load");
        loadUserBtn.setOnMouseClicked(new LoadUserHandler());

        this.add(scenetitle, 0, 0, 2, 1);
        this.add(username, 0, 1);
        this.add(usernamebox, 1, 1);
        this.add(loadUserBtn, 2, 1);

        //simple Title label & pos
        scenetitle1 = new Text("Please modify desired fields regarding account information.");
        scenetitle1.setFont(Font.font("Verdana"));
        this.add(scenetitle1, 0, 7, 2, 1);
        
        //full name 
        name = new Label("Enter employee full name:");
        this.add(name, 0, 8);
		namebox = new TextField();	
        this.add(namebox, 1, 8);       

        //work position
        position = new Label("Choose employee position:");
        this.add(position, 0, 9);
        posbox = new ComboBox<>();
        posbox.getItems().addAll(Arrays.asList(Position.values()));
        this.add(posbox, 1, 9);

        //work email
        email = new Label("Enter employee email:");
        this.add(email, 0, 10);
        emailbox = new TextField();
        this.add(emailbox, 1, 10);

        //seniority in work domain
        seniority = new Label("Choose employee seniority:");
        this.add(seniority, 0, 11);
        senbox = new ComboBox<>();
        senbox.getItems().addAll(Arrays.asList(Seniority.values()));
        this.add(senbox, 1, 11);

        //base salary
        salary = new Label("Enter employee base salary:");
        this.add(salary, 0, 12);
        salarybox = new TextField();
        this.add(salarybox, 1, 12);
        
        //yearly leave days
        leaveDays = new Label("Enter yearly leave days:");
        this.add(leaveDays, 0, 13);
        leaveDaysbox = new TextField();
        this.add(leaveDaysbox, 1, 13);

        //account type
        accType = new Label("Choose account type:");
        this.add(accType, 0, 14);
        accTypebox = new ComboBox<>();
        accTypebox.getItems().addAll(Arrays.asList(AccountType.values()));
        this.add(accTypebox, 1, 14);

        saveBtn = new Button("Save");
        saveBtn.setOnMouseClicked(new SaveUserHandler());
        this.add(saveBtn, 0, 15);
        
        ServiceHandler.setOnSucceededHandler(ServiceID.RETRIEVEUSERSERVICE, new UserLoadedHandler());
        ServiceHandler.setOnSucceededHandler(ServiceID.UPDATEUSERSERVICE, new UserSavedHandler());

    }

    private class SaveUserHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {

            if(selectedUser == null){
                AlertBoxLogIn.display("Alert!", "Must first load a user!");
                return;
            }

            selectedUser.setName(namebox.getText());
            selectedUser.setPosition(posbox.getSelectionModel().getSelectedItem());
            selectedUser.setEmail(emailbox.getText());
            selectedUser.setSeniority(senbox.getSelectionModel().getSelectedItem());
            selectedUser.setSalary(Double.valueOf(salarybox.getText()));
            selectedUser.setLeaveDays(Integer.valueOf(leaveDaysbox.getText()));
            selectedUser.setaccountType(accTypebox.getSelectionModel().getSelectedItem());

            ServiceHandler.setValues(ServiceID.UPDATEUSERSERVICE, selectedUser);
            ServiceHandler.startService(ServiceID.UPDATEUSERSERVICE);
            AlertBoxLogIn.display("Alert", "Working...");

        }

    }

    
    private class UserSavedHandler implements EventHandler<Event>{

        @Override
        public void handle(Event event) {
            if((boolean)ServiceHandler.getValues(ServiceID.UPDATEUSERSERVICE)){
                AlertBoxLogIn.display("Alert", "Account updated!");
                return;
            }else{
                AlertBoxLogIn.display("Alert", "Could not update account information!");
            }

        }

    }

    


    private class LoadUserHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            
            ServiceHandler.setValues(ServiceID.RETRIEVEUSERSERVICE, usernamebox.getText());
            ServiceHandler.startService(ServiceID.RETRIEVEUSERSERVICE);
            AlertBoxLogIn.display("Alert", "Loading...");
        }

    }

    private class UserLoadedHandler implements EventHandler<Event>{

        @Override
        public void handle(Event event) {

            selectedUser = ServiceHandler.getValues(ServiceID.RETRIEVEUSERSERVICE);

            if(selectedUser == null){
                AlertBoxLogIn.display("Alert", "Employee not found or permission denied!");
                return;
            }

            AlertBoxLogIn.closeWindow();

            namebox.setText(selectedUser.getName());
            posbox.getSelectionModel().select(selectedUser.getPosition());
            emailbox.setText(selectedUser.getEmail());
            senbox.getSelectionModel().select(selectedUser.getSeniority());
            salarybox.setText(String.valueOf(selectedUser.getSalary()));
            leaveDaysbox.setText(String.valueOf(selectedUser.getLeaveDays()));
            accTypebox.getSelectionModel().select(selectedUser.getaccountType());

        }

    }

}