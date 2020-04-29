package SEF_HR_APP;

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

    public LoginScene(double width, double height,MainLogin app) {
        super(new GridPane(), width, height);
    
        /*used Grid panelling layout style for getting user input
		instace for Grid Panel with positioning on scene
		and positioning each block
		*/
        GridPane grid = (GridPane) this.getRoot();

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

        //simple Title label & pos
		Text scenetitle = new Text("Welcome. Please log in.");
		grid.add(scenetitle, 0, 0, 2, 1);

        //creating Username block & pos
		Label userName = new Label("Username:");
		grid.add(userName, 0, 1);

		TextField userBox = new TextField();
		grid.add(userBox, 1, 1);

        //creating Password block & pos
		Label pass = new Label("Password:");
		grid.add(pass, 0, 2);

		PasswordField passBox = new PasswordField();
		grid.add(passBox, 1, 2);

        //instance for "Sign in" button 
		Button signin_button = new Button("Sign in");
        HBox hbtn = new HBox(10);
        
        //positioning and sizing "Sign in" button
		hbtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbtn.getChildren().add(signin_button);
        grid.add(hbtn, 1, 4);
        signin_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent e){
				
                //app.transToLogin2();
                TEMPAlertBoxLogIn.display("Log In Alert", "Login process was successful");
				
            }

        });
        this.setRoot(grid);
    }

    /*private boolean IsuserBoxEmpty(TextField user)
	{
		if(user.getText().trim().isEmpty())
			return false;
		else
			return true;
	}*/




}