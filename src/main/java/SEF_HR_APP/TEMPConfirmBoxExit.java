package SEF_HR_APP;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class TEMPConfirmBoxExit {

    //Create variable
    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(150);

        Label label = new Label();
        label.setText(message);

        //Create exit option buttons
        Button yesButton = new Button("Yes");
        yesButton.setMinWidth(20);
        Button noButton = new Button("No");
        noButton.setMinWidth(20);

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        HBox layout = new HBox(10);

        //add and position message
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);



        //instance of scene
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}