package SEF_HR_APP.frontend.scenes;

import java.util.ArrayList;
import java.util.Arrays;

import SEF_HR_APP.backend.ServiceHandler;
import SEF_HR_APP.backend.ServiceHandler.ServiceID;
import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.datamodels.activity.ActivityStatus;
import SEF_HR_APP.backend.datamodels.activity.MonthType;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.frontend.popUpBoxes.AlertBoxLogIn;
import SEF_HR_APP.interfaces.RetrieveActivityInfo;
import SEF_HR_APP.interfaces.RetrieveActivityInfoResponse;
import SEF_HR_APP.interfaces.SetActivityStatus;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ViewUserActivityScene extends GridPane {

    private Label month;
    private ComboBox<MonthType> monthBox;
    private Label username;
    private TextField usernameBox;
    private Button loadBtn;
    private ArrayList<Text> entries;
    private ViewUserActivityScene instance;
    private User selectedUser;
    private Label review;
    private ComboBox<ActivityStatus> reviewBox;
    private Button reviewBtn;
    private ActivityInformation activity;


    public ViewUserActivityScene() {
        super();

        this.instance = this;
        this.setAlignment(Pos.TOP_LEFT);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        
        username = new Label("Username :");
        usernameBox = new TextField();

        this.add(username, 0, 0);
        this.add(usernameBox, 1, 0);

        month = new Label("Month :");
        monthBox = new ComboBox<>();
        monthBox.getItems().addAll(Arrays.asList(MonthType.values()));

        this.add(month, 0, 1);
        this.add(monthBox, 1, 1);

        loadBtn = new Button("Load");
        loadBtn.setOnMouseClicked(new LoadUserHandler());
        this.add(loadBtn, 2, 1, 2, 1);

        review = new Label("Review status :");
        reviewBox = new ComboBox<>();
        reviewBox.getItems().addAll(Arrays.asList(ActivityStatus.values()));
        reviewBtn = new Button("Set status");
        reviewBtn.setOnMouseClicked(new SetReviewHandler());
        this.add(review,0,2);
        this.add(reviewBox,1,2);
        this.add(reviewBtn, 2, 2);

        ServiceHandler.setOnSucceededHandler(ServiceID.RETRIEVEACTIVITYSERVICE,new ActivityLoadedHandler());
        ServiceHandler.setOnSucceededHandler(ServiceID.RETRIEVEUSERSERVICE, new UserLoadedHandler());
        ServiceHandler.setOnSucceededHandler(ServiceID.UPDATEREVIEWSTATUSSERVICE, new SetReviewSuccessHandler());

        entries = new ArrayList<>();

    }

    private class ActivityLoadedHandler implements EventHandler<Event>{

        @Override
        public void handle(Event event) {
           
            AlertBoxLogIn.closeWindow();
           
            for(Text t : entries)
                instance.getChildren().remove(t);
            entries.clear();
            instance.requestLayout();

            activity =((RetrieveActivityInfoResponse) ServiceHandler.getValues(ServiceID.RETRIEVEACTIVITYSERVICE)).getActivityInformation();
            if(activity == null){
                AlertBoxLogIn.display("Alert", "No activity found for selected month!");
                return;
            }

            double totalSalary = 0;

            Text user = new Text("Activity shown for " + selectedUser.getName());
            entries.add(user);
            instance.add(user, 0,instance.getRowCount(), 3, 2);

            for(int i = 0 ; i < activity.getOptionCount() ; i++){
                PayOption opt = activity.getOption(i);
                double bonusReceived = selectedUser.getSalary();
                int hour = activity.getHours(i);
                bonusReceived *= hour * opt.getPercentage() /100;
                Text tmpText = new Text();
                tmpText.setText(opt.toString()+" : "+hour + " hours - pay : " + bonusReceived);
                totalSalary += bonusReceived;
                entries.add(tmpText);
                instance.add(tmpText, 0, instance.getRowCount(),3,1);
            }
            Text total = new Text("Total salary for " + monthBox.getSelectionModel().getSelectedItem().getStringRepresentation() + " : "+totalSalary );
            entries.add(total);
            instance.add(total, 0,instance.getRowCount(), 3, 1);
            reviewBox.getSelectionModel().select(activity.getStatus());
        }
        
    }

    private class LoadUserHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {

            if(monthBox.getSelectionModel().getSelectedItem().monthDifferenceToCurrent() >= 6){
                AlertBoxLogIn.display("Alert", "You can request activity information only for the last 6 months!");
                return;
            }
            
            ServiceHandler.setValues(ServiceID.RETRIEVEUSERSERVICE, usernameBox.getText());
            ServiceHandler.startService(ServiceID.RETRIEVEUSERSERVICE);
            AlertBoxLogIn.display("Alert", "Loading...");
        }

    }

    private class UserLoadedHandler implements EventHandler<Event>{

        @Override
        public void handle(Event event) {

            selectedUser = ServiceHandler.getValues(ServiceID.RETRIEVEUSERSERVICE);

            if(selectedUser == null){
                AlertBoxLogIn.display("Alert", "Account not found or permission denied!");
                return;
            }

            ServiceHandler.setValues(ServiceID.RETRIEVEACTIVITYSERVICE, new RetrieveActivityInfo(selectedUser, monthBox.getSelectionModel().getSelectedItem()));
            ServiceHandler.startService(ServiceID.RETRIEVEACTIVITYSERVICE);

        }

    }

    private class SetReviewHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            activity.setStatus(reviewBox.getSelectionModel().getSelectedItem());
            ServiceHandler.setValues(ServiceID.UPDATEREVIEWSTATUSSERVICE,new SetActivityStatus(activity,selectedUser));
            ServiceHandler.startService(ServiceID.UPDATEREVIEWSTATUSSERVICE);
            AlertBoxLogIn.display("Alert", "Working...");
        }

    }

    private class SetReviewSuccessHandler implements EventHandler<Event>{

        @Override
        public void handle(Event event) {
            if((boolean)ServiceHandler.getValues(ServiceID.UPDATEREVIEWSTATUSSERVICE)){
                AlertBoxLogIn.display("Alert", "Notification sent!");
            }else{
                AlertBoxLogIn.display("Alert", "Operation failed!");
            }
        }

    }

}