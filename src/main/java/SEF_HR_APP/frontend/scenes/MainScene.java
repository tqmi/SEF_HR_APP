package SEF_HR_APP.frontend.scenes;

import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.frontend.MainLogin;
import SEF_HR_APP.frontend.popUpBoxes.TEMPConfirmBox;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

public class MainScene extends Scene {

	private SplitPane split;
	private VBox menuPanel;
	private Button menuOpt1;
	private Button menuOpt2;
	private Button menuOpt3;
	private Button menuOpt4;
	private Button menuOpt5;
	private Button menuOpt6;
	private Button menuOpt7;
	private Button menuOpt8;
	private Button menuOpt9;
	private Button menuOpt10;
	private Button logout_button;
	private GridPane right;
	private MainLogin app;


    public MainScene(double width, double height, MainLogin app) {
       
		super(new SplitPane(), width, height);
		this.app = app;

		//creating instance of a 2-split main screen
		split = (SplitPane) this.getRoot();

		//creating left menu panel with option buttons and logout button
		menuPanel = new VBox();

		menuOpt1 = new Button("Create Account");  //admin, operator  
		menuOpt2 = new Button("Delete Account");  //admin, operator
		menuOpt3 = new Button("Modify Account\n      Details");  //admin, operator
		menuOpt4 = new Button("Add Pay Option");  //operator
		menuOpt5 = new Button("Delete Pay Option");  //operator
		menuOpt6 = new Button("Modify Pay Option");  //operator
		menuOpt7 = new Button("View User Activity");  //operator, supervisor
		menuOpt8 = new Button("Request Activity\n     Information");  //employee, supervisor
		menuOpt9 = new Button("Provide Activity\n     Information");  //employee, supervisor
		menuOpt10 = new Button("Review Activity Data");  //operator, supervisor
		logout_button = new Button("Logout");

		//switch statement for providing user with proper commands based on access rights
		switch(User.getCurrentUser().getaccountType())
		{
			case ADMIN : 
				menuPanel.getChildren().addAll(menuOpt1, menuOpt2, menuOpt3, logout_button);
				break;

			case SUPERVISOR :
				menuPanel.getChildren().addAll(menuOpt7, menuOpt8, menuOpt9, menuOpt10, logout_button);
				break;

			case SUPERVISOR_OPERATOR :
				menuPanel.getChildren().addAll(menuOpt1, menuOpt2, menuOpt3, menuOpt4, menuOpt5, menuOpt6, menuOpt7, menuOpt8, menuOpt9, menuOpt10, logout_button);
				break;

			case EMPLOYEE :
				menuPanel.getChildren().addAll(menuOpt9, menuOpt8, logout_button);
				break;

			case EMPLOYEE_OPERATOR :
				menuPanel.getChildren().addAll(menuOpt1, menuOpt2, menuOpt3, menuOpt4, menuOpt5, menuOpt6, menuOpt7, menuOpt8, menuOpt9, menuOpt10, logout_button);
				break;

		}

		
		//setting elements position on menu panel
		menuPanel.setSpacing(20);
		menuPanel.setAlignment(Pos.TOP_CENTER);
		menuPanel.setPadding(new Insets(25, 0, 0, 0));

		
		//creating right panel where main activity will take place
		right = new GridPane();

		menuOpt1.setOnMouseClicked(new createAccHandler());


		//setting and fixing division line between panels (can be flexible)
		split.setDividerPositions(0.25);
		menuPanel.maxWidthProperty().bind(split.widthProperty().multiply(0.25));

		
		split.getItems().addAll(menuPanel, right);


		//call for Event Handler to begin logout process
		logout_button.setOnMouseClicked(new logoutHandler());
		

		this.setRoot(split);

    }


	/*creating event for logout button
			if clicked -> pop up will appear
			when user logs out, will be sent back to login screen
		*/
	private class logoutHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e)
		{
			if(TEMPConfirmBox.display("Log Out Alert", "Are you sure you want to logout?"))
					app.transToLoginScene();
		}
	}

	private class createAccHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e)
		{
			split.getItems().remove(right);
			right = new CreateAccountScene();
			split.getItems().add(right);
		}
	}

}