package SEF_HR_APP.frontend;

import SEF_HR_APP.backend.ServiceHandler;
import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.frontend.popUpBoxes.TEMPConfirmBox;
import SEF_HR_APP.frontend.scenes.LoginScene;
import SEF_HR_APP.frontend.scenes.MainScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainLogin extends Application {

	public static void main(String[] args) 
	{
		launch(args);
	}
	
	
	private Stage window;
	private Scene scene;


	@Override
	public void init() throws Exception {
		ServiceHandler.initialize();
        DBHandler.connectDB();
		super.init();
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		window = primaryStage;
		window.setTitle("SHA Login");

		//lambda expression for shutting down program
		window.setOnCloseRequest(e->{
			e.consume();
			closeProgram();
		});

		//instance for Login Scene
		scene = new LoginScene(500, 350, this);
		
		window.setScene(scene);
		window.show();
	}
	
	@Override
	public void stop() throws Exception {
		DBHandler.close();
		super.stop();
	}

	private void closeProgram()
	{

		Boolean answer = TEMPConfirmBox.display("Close program alert", "Are you sure you want to exit?");
		/*
			code to be parsed after user 
			sends command for closing program
			i.e. saving on db
		*/
		if(answer){
			window.close();
		}
	}	

	//implement transition to main scene
	public void transToMainScene(){
		Scene scene = new MainScene(500, 350, this);
		window.setTitle("HRSolution");
		window.setScene(scene);
		window.show();
	}

	//implement transition to login scene
	public void transToLoginScene(){
		User.resetCurrentUser();
		Scene scene = new LoginScene(500, 350, this);
		window.setTitle("SHA Login");
		window.setScene(scene);
		window.show();
	}
}