package SEF_HR_APP.frontend.scenes;

import SEF_HR_APP.backend.ServiceHandler;
import SEF_HR_APP.backend.ServiceHandler.ServiceID;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.frontend.MainLogin;
import SEF_HR_APP.frontend.popUpBoxes.TEMPConfirmBox;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
	private Button logout_button;
	private GridPane right;
	private ScrollPane scroll;
	private MainLogin app;


    public MainScene(double width, double height, MainLogin app) {
       
		super(new SplitPane(), width, height);
		this.app = app;

		//creating instance of a 2-split main screen
		split = (SplitPane) this.getRoot();

		//creating left menu panel with option buttons and logout button
		scroll = new ScrollPane();
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
		logout_button = new Button("Logout");

		//switch statement for providing user with proper commands based on access rights
		switch(User.getCurrentUser().getaccountType())
		{
			case ADMIN : 
				menuPanel.getChildren().addAll(menuOpt1, menuOpt2, menuOpt3, logout_button);
				break;

			case SUPERVISOR :
				menuPanel.getChildren().addAll(menuOpt7, menuOpt8, menuOpt9, logout_button);
				break;

			case SUPERVISOR_OPERATOR :
				menuPanel.getChildren().addAll(menuOpt1, menuOpt2, menuOpt3, menuOpt4, menuOpt5, menuOpt6, menuOpt7, menuOpt8, menuOpt9, logout_button);
				break;

			case EMPLOYEE :
				menuPanel.getChildren().addAll(menuOpt9, menuOpt8, logout_button);
				break;

			case EMPLOYEE_OPERATOR :
				menuPanel.getChildren().addAll(menuOpt1, menuOpt2, menuOpt3, menuOpt4, menuOpt5, menuOpt6, menuOpt7, menuOpt8, menuOpt9, logout_button);
				break;

		}

		
		//setting elements position on menu panel
		menuPanel.setSpacing(20);
		menuPanel.setAlignment(Pos.TOP_CENTER);
		menuPanel.setPadding(new Insets(25, 0, 0, 0));

		
		//creating right panel where main activity will take place
		right = new GridPane();

		menuOpt1.setOnMouseClicked(new CreateAccHandler());
		menuOpt2.setOnMouseClicked(new DeleteAccountHandler());
		menuOpt3.setOnMouseClicked(new ModifyAccountHandler());
		menuOpt4.setOnMouseClicked(new AddPayOptionHandler());
		menuOpt7.setOnMouseClicked(new ViewUserActivityHandler());
		menuOpt8.setOnMouseClicked(new RequestActivityHandler());
		menuOpt9.setOnMouseClicked(new ProvideActivityHandler());
		
		//setting and fixing division line between panels (can be flexible)
		split.setDividerPositions(0.25);
		scroll.maxWidthProperty().bind(split.widthProperty().multiply(0.25));

		//setting Scrolling properties to encapsulate menuPanel
		scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
		//wraper for centering VBox
		StackPane wraper = new StackPane();
		wraper.getChildren().add(menuPanel);
		wraper.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
        scroll.getViewportBounds().getWidth(), scroll.viewportBoundsProperty()));
		scroll.setContent(wraper);
		
		split.getItems().addAll(scroll, right);

		
		//call for Event Handler to begin logout process
		logout_button.setOnMouseClicked(new LogoutHandler());
		

		this.setRoot(split);

    }


	/*creating event for logout button
			if clicked -> pop up will appear
			when user logs out, will be sent back to login screen
		*/
	private class LogoutHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e)
		{
			if(TEMPConfirmBox.display("Log Out Alert", "Are you sure you want to logout?"))
					app.transToLoginScene();
		}
	}

	private class CreateAccHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e)
		{
			split.getItems().remove(right);
			right = new CreateAccountScene();
			split.getItems().add(right);
		}
	}

	private class AddPayOptionHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e)
		{
			split.getItems().remove(right);
			right = new AddPayOptionScene();
			split.getItems().add(right);
		}
	}

	private class ProvideActivityHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			split.getItems().remove(right);
			right = new ProvideActivityScene();
			split.getItems().add(right);
		}

	}

	private class RequestActivityHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			split.getItems().remove(right);
			right = new RequestActivityScene();
			split.getItems().add(right);
		}

	}

	private class ViewUserActivityHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			split.getItems().remove(right);
			right = new ViewUserActivityScene();
			split.getItems().add(right);
		}

	}

	private class ModifyAccountHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			split.getItems().remove(right);
			right = new ModifyAccountScene();
			split.getItems().add(right);
		}

	}


	private class DeleteAccountHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event)
		{
			split.getItems().remove(right);
			right = new DeleteAccountScene();
			split.getItems().add(right);
		}
	}
}