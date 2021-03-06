package SEF_HR_APP.frontend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import SEF_HR_APP.backend.ServiceHandler;
import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.backend.mail.MailService;
import SEF_HR_APP.frontend.popUpBoxes.TEMPConfirmBox;
import SEF_HR_APP.frontend.scenes.LoginScene;
import SEF_HR_APP.frontend.scenes.MainScene;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainLogin extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private Stage window;
	private Scene scene;
	private Image icon;
	private FileInputStream stream;

	@Override
	public void init() throws Exception {
		if(!DBHandler.connectDB()){
			System.exit(1);
		}
		MailService.initialize();
		super.init();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("PayHelp Login");

		//call for Event Handler to close program
		window.setOnCloseRequest(new closeProgramHandler());

		// instance for Login Scene
		scene = new LoginScene(500, 350, this);

		try {
			stream = new FileInputStream("designElms\\icon.png");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		icon = new Image(stream);

		window.getIcons().add(icon);

		window.setScene(scene);
		window.show();
	}

	@Override
	public void stop() throws Exception {
		DBHandler.close();
		super.stop();
	}

	private void closeProgram() {

		Boolean answer = TEMPConfirmBox.display("Close program alert", "Are you sure you want to exit?");
		/*
		 * code to be parsed after user sends command for closing program i.e. saving on
		 * db
		 */
		if (answer) {
			window.close();
		}
	}

	// implement transition to main scene
	public void transToMainScene() {
		Scene scene = new MainScene(600, 600, this);
		window.setTitle("PayHelp");
		window.setScene(scene);
		window.show();
	}

	// implement transition to login scene
	public void transToLoginScene() {
		User.resetCurrentUser();
		Scene scene = new LoginScene(500, 350, this);
		window.setTitle("PayHelp Login");
		window.setScene(scene);
		window.show();
	}

	//implementation of Window-type Event Handler for shutting down program 
	private class closeProgramHandler implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent e)
		{
			e.consume();
			closeProgram();			
		}
	}
}