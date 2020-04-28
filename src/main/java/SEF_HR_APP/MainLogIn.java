package SEF_HR_APP;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainLogIn extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	Stage window;

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("SHA Login");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Welcome. Please log in.");
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("Username:");
		grid.add(userName, 0, 1);

		TextField userBox = new TextField();
		grid.add(userBox, 1, 1);

		Label pass = new Label("Password:");
		grid.add(pass, 0, 2);

		PasswordField passBox = new PasswordField();
		grid.add(passBox, 1, 2);

		Button signin_button = new Button("Sign in");
		HBox hbtn = new HBox(10);
		
		hbtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbtn.getChildren().add(signin_button);
		grid.add(hbtn, 1, 4);

		Scene scene = new Scene(grid, 300, 250);
		
		window.setScene(scene);
		window.show();
	}

}

