package SEF_HR_APP.frontend.popUpBoxes;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;

public class TEMPConfirmBox {

    //Create variable
    static boolean answer;

    private static Stage window;
    private static Label label;
    private static Button yesButton;
    private static Button noButton;
    private static HBox layout;
    private static Scene scene;

    public static boolean display(String title, String message) {

        if(window != null)
            window.close();

        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(150);

        label = new Label();
        label.setText(message);

        //Create exit option buttons
        yesButton = new Button("Yes");
        yesButton.setMinWidth(20);
        noButton = new Button("No");
        noButton.setMinWidth(20);

        //Clicking will set answer and close window
        yesButton.setOnAction(new EventHandler<ActionEvent>(){
        
            @Override
            public void handle(ActionEvent event) {
                answer = true;
                window.close();
            }
        }); 

        noButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                answer = false;
                window.close();
            }
        }); 


        //add and position message
        layout = new HBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        //instance of scene
        scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    public static void closeWindow(){
        if(window != null)
            window.close();
        window = null;
    }
}