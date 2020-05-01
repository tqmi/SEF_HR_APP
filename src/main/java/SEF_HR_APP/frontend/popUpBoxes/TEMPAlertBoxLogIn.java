package SEF_HR_APP.frontend.popUpBoxes;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;

public class TEMPAlertBoxLogIn {

    private static Stage window;
    private static Scene scene;
    private static Label label;
    private static Button closeButton;
    private static VBox layout;

    public static void display(String title, String message) {
        
        if(window != null)
            window.close();
        
        window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(150);

        label = new Label();
        label.setText(message);
        closeButton = new Button("Close Pop-up window");
        
        closeButton.setOnAction(new EventHandler<ActionEvent>(){
        
            @Override
            public void handle(ActionEvent event) {
                window.close();    
                
            }
        });

        layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void closeWindow(){
        if(window != null)
            window.close();
        window = null;
    }

}