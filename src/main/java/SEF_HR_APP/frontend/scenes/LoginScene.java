package SEF_HR_APP.frontend.scenes;

import SEF_HR_APP.backend.ServiceHandler;
import SEF_HR_APP.backend.ServiceHandler.ServiceID;
import SEF_HR_APP.frontend.MainLogin;
import SEF_HR_APP.frontend.popUpBoxes.TEMPAlertBoxLogIn;
import SEF_HR_APP.interfaces.LoginInfo;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LoginScene extends Scene {

	private GridPane grid;
	private Text scenetitle;

	private Label userName;
	private TextField userBox = new TextField();

	private Label pass;
	private PasswordField passBox = new PasswordField();

	private Button signin_button;
	private HBox hbtn = new HBox(10);



    public LoginScene(double width, double height,MainLogin app) {
        super(new GridPane(), width, height);
    
        /*used Grid panelling layout style for getting user input
		instace for Grid Panel with positioning on scene
		and positioning each block
		*/
        grid = (GridPane) this.getRoot();

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

        //simple Title label & pos
		scenetitle = new Text("Welcome. Please log in.");
		grid.add(scenetitle, 0, 0, 2, 1);

        //creating Username block & pos
		userName = new Label("Username:");
		grid.add(userName, 0, 1);	
		grid.add(userBox, 1, 1);

        //creating Password block & pos
		pass = new Label("Password:");
		grid.add(pass, 0, 2);
		grid.add(passBox, 1, 2);

        //instance for "Sign in" button 
		signin_button = new Button("Sign in");
        
        //positioning and sizing "Sign in" button
		hbtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbtn.getChildren().add(signin_button);
		grid.add(hbtn, 1, 4);
		
		ServiceHandler.setOnSucceededHandler(ServiceID.AUTHENTICATIONSERVICE,new AuthenticationSucceededHandler());

        signin_button.setOnMouseClicked(new ButtonCickedHandler());
		
        this.setRoot(grid);
	}
	


	private class ButtonCickedHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {

			ServiceHandler.setValues(ServiceID.AUTHENTICATIONSERVICE, new LoginInfo(userBox.getText(), passBox.getText()));
			ServiceHandler.startService(ServiceID.AUTHENTICATIONSERVICE);

			TEMPAlertBoxLogIn.display("Log In Alert", "Authenticating");

		}
		
	}

	private class AuthenticationSucceededHandler implements EventHandler<Event>{

		@Override
			public void handle(Event event) {
				if((boolean) ServiceHandler.getValues(ServiceID.AUTHENTICATIONSERVICE)) {
					TEMPAlertBoxLogIn.closeWindow();
					app.transToMainScene();
				}
				else{
					TEMPAlertBoxLogIn.display("Log In Alert", "Failed Login");
				}
			}

	}
}

