package SEF_HR_APP.frontend;

import SEF_HR_APP.frontend.popUpBoxes.TEMPConfirmBox;
import SEF_HR_APP.frontend.scenes.LoginScene;
import SEF_HR_APP.interfaces.SignalHandler;
import SEF_HR_APP.interfaces.signals.ApplicationClosingSignal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import SEF_HR_APP.frontend.scenes.MainScene;

public class MainLogin extends Application {

	public static void main(String[] args) 
	{
		launch(args);
	}
	
	
	private Stage window;


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
		Scene scene = new LoginScene(500, 350, this);
		
		window.setScene(scene);
		window.show();
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
			SignalHandler.setSignalBackend(new ApplicationClosingSignal());
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
		Scene scene = new LoginScene(500, 350, this);
		window.setTitle("SHA Login");
		window.setScene(scene);
		window.show();
	}
}