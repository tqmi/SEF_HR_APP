package SEF_HR_APP.frontend.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import SEF_HR_APP.frontend.MainLogin;
import SEF_HR_APP.frontend.popUpBoxes.TEMPConfirmBox;

public class MainScene extends Scene {

	private SplitPane split;
	private VBox menuPanel;
	private Button menuOpt1;
	private Button menuOpt2;
	private Button menuOpt3;
	private Button logout_button;
	private StackPane right;

    public MainScene(double width, double height, MainLogin app) {
       
		super(new SplitPane(), width, height);

		//creating instance of a 2-split main screen
		split = (SplitPane) this.getRoot();



		//creating left menu panel with TBA option buttons and logout button
		menuPanel = new VBox();
		menuOpt1 = new Button("Menu Option 1");
		menuOpt2 = new Button("Menu Option 2");
		menuOpt3 = new Button("Menu Option 3");
		logout_button = new Button("Logout");

		//setting elements on panel
		menuPanel.getChildren().addAll(menuOpt1, menuOpt2, menuOpt3, logout_button);
		menuPanel.setSpacing(20);
		menuPanel.setAlignment(Pos.TOP_CENTER);
		menuPanel.setPadding(new Insets(25, 0, 0, 0));
		
		//creating left panel where main activity will take place
		right = new StackPane(new Label("Operation side"));

		
		//setting and fixing division line between panels (can be flexible)
		split.setDividerPositions(0.25);
		menuPanel.maxWidthProperty().bind(split.widthProperty().multiply(0.25));

		split.getItems().addAll(menuPanel, right);


		/*creating event for logout button
			if clicked -> pop up will appear
			when user logs out, will be sent back to login screen
		*/
		logout_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent e){
				
				if(TEMPConfirmBox.display("Log Out Alert", "Are you sure you want to logout?"))
					app.transToLoginScene();
			
			}

		});

		this.setRoot(split);

    }




}