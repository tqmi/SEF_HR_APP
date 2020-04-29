package SEF_HR_APP.frontend.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import SEF_HR_APP.frontend.MainLogin;

public class MainScene extends Scene {

    public MainScene(double width, double height, MainLogin app) {
        super(new GridPane(), width, height);
    
        GridPane grid = (GridPane) this.getRoot();

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("This is the main screen of the program");
		grid.add(scenetitle, 0, 0, 2, 1);

        this.setRoot(grid);
    }




}