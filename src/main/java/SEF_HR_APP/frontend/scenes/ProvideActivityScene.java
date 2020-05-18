package SEF_HR_APP.frontend.scenes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import SEF_HR_APP.backend.ServiceHandler;
import SEF_HR_APP.backend.ServiceHandler.ServiceID;
import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.datamodels.activity.ActivityStatus;
import SEF_HR_APP.backend.datamodels.activity.MonthType;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import SEF_HR_APP.frontend.popUpBoxes.AlertBoxLogIn;
import SEF_HR_APP.interfaces.RetrieveActivityInfo;
import SEF_HR_APP.interfaces.RetrieveActivityInfoResponse;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ProvideActivityScene extends GridPane {
    

    private Text scenetitle;
    private Label month;
    private ComboBox<MonthType> monthBox;
    private Button addNewBtn;
    private Button loadBtn;
    private ArrayList<ComboBox<PayOption>> options;
    private ArrayList<TextField> hours;
    private ProvideActivityScene instance;
    private ArrayList<PayOption> optionList;
    private ActivityInformation activity;
    private Button saveBtn;
    private DeleteHandler deleteHandler;
    private ArrayList<Button> deleteBtns;

    public ProvideActivityScene() {

        super();
        this.instance = this;

        this.setAlignment(Pos.TOP_LEFT);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        
        deleteHandler = new DeleteHandler();


        scenetitle = new Text("Please choose desired month and press + for adding option.");
        scenetitle.setFont(Font.font("Verdana"));
        month = new Label("Month:");
        monthBox = new ComboBox<>();
        monthBox.getItems().addAll(Arrays.asList(MonthType.values()));

        //this.add(scenetitle, 0, 0, 2, 1);
        this.add(month, 0, 1);
        this.add(monthBox, 1, 1);
        
        loadBtn = new Button("Load");
        loadBtn.setOnMouseClicked(new LoadActivityHandler());
        ServiceHandler.setOnSucceededHandler(ServiceID.RETRIEVEACTIVITYSERVICE,new ActivityLoadedHandler());
        this.add(loadBtn, 2, 1);

        saveBtn = new Button("Save");
        saveBtn.setOnMouseClicked(new StoreActivityHandler());
        ServiceHandler.setOnSucceededHandler(ServiceID.STOREACTIVITYSERVICE,new ActivityStoredHandler());
        this.add(saveBtn, 3, 1);

        addNewBtn = new Button("Add Entry");
        addNewBtn.setOnMouseClicked(new AddNewOptionHandler());
        addNewBtn.maxWidthProperty().bind(this.widthProperty());
        this.add(addNewBtn, 0, 3);

        options = new ArrayList<>();
        hours = new ArrayList<>();
        deleteBtns = new ArrayList<>();
    }

    private class ActivityLoadedHandler implements EventHandler<Event>{

        @Override
        public void handle(Event event) {
            AlertBoxLogIn.closeWindow();
            for(TextField t : hours)
                instance.getChildren().remove(t);
            hours.clear();
            for(ComboBox t : options)
                instance.getChildren().remove(t);
            options.clear();
            for(Button t : deleteBtns)
                instance.getChildren().remove(t);
            deleteBtns.clear();

            instance.requestLayout();

            activity =((RetrieveActivityInfoResponse) ServiceHandler.getValues(ServiceID.RETRIEVEACTIVITYSERVICE)).getActivityInformation();
            if(activity == null){
                activity = new ActivityInformation(monthBox.getSelectionModel().getSelectedItem());
            }

            optionList = ((RetrieveActivityInfoResponse) ServiceHandler.getValues(ServiceID.RETRIEVEACTIVITYSERVICE)).getOptions();


            for(int i = 0 ; i < activity.getOptionCount() ; i++){
                PayOption opt = activity.getOption(i);
                TextField tmph = new TextField(""+activity.getHours(i));
                hours.add(tmph);
                ComboBox<PayOption> tmpbox = new ComboBox<>();
                tmpbox.getItems().addAll(optionList);
                tmpbox.getSelectionModel().select(opt);
                options.add(tmpbox);
                Button deleteBtn = new Button("X");
                deleteBtn.setOnMouseClicked(deleteHandler);
                deleteBtns.add(deleteBtn);

                instance.add(tmpbox, 0, instance.getRowCount());
                instance.add(tmph, 1, instance.getRowCount()-1);
                instance.add(deleteBtn,2,instance.getRowCount()-1);

            }
        }
        
    }

    private class LoadActivityHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {

            if(monthBox.getSelectionModel().getSelectedItem().monthDifferenceToCurrent() >= 2){
                AlertBoxLogIn.display("Alert", "You can provide activity information only for the last 2 months!");
                return;
            }


            ServiceHandler.setValues(ServiceID.RETRIEVEACTIVITYSERVICE, new RetrieveActivityInfo(null, monthBox.getSelectionModel().getSelectedItem()));
            ServiceHandler.startService(ServiceID.RETRIEVEACTIVITYSERVICE);
            AlertBoxLogIn.display("Alert", "Loading...");
        }

    }

    private class AddNewOptionHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            
            TextField tmph = new TextField();
            hours.add(tmph);
            ComboBox<PayOption> tmpbox = new ComboBox<>();
            tmpbox.getItems().addAll(optionList);
            options.add(tmpbox);
            Button deleteBtn = new Button("X");
            deleteBtn.setOnMouseClicked(deleteHandler);
            deleteBtns.add(deleteBtn);

            instance.add(tmpbox, 0, instance.getRowCount());
            instance.add(tmph, 1, instance.getRowCount()-1);
            instance.add(deleteBtn,2,instance.getRowCount()-1);
        }
    }

    private class DeleteHandler implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent event) {
            Button cbtn = (Button)event.getSource();
            int row = instance.getRowIndex(cbtn);
            for(TextField t : hours){
                if(instance.getRowIndex(t) == row){
                    instance.getChildren().remove(t);
                    hours.remove(t);
                    break;
                }
            }
            for(ComboBox<PayOption> t : options){
                if(instance.getRowIndex(t) == row){
                    instance.getChildren().remove(t);
                    options.remove(t);
                    break;
                }
            }
            instance.getChildren().remove(cbtn);
            instance.requestLayout();
        }

    }
    
    private class StoreActivityHandler implements EventHandler<Event>{

        @Override
        public void handle(Event event) {
           
            if(monthBox.getSelectionModel().getSelectedItem().monthDifferenceToCurrent() >= 2){
                AlertBoxLogIn.display("Alert", "You can provide activity information only for the last 2 months!");
                return;
            }
            //regex for numeric fields
            Pattern numericPattern = Pattern.compile("\\d+(\\.\\d+)?");
            
            for(int i = 0 ; i < options.size() ; i++){
                if(options.get(i).getSelectionModel().getSelectedItem() == null || hours.get(i).getText().trim().isEmpty() || !numericPattern.matcher(hours.get(i).getText().trim()).matches()){
                    AlertBoxLogIn.display("Alert", "All fields are mandatory!");
                    return;
                }
            }

            activity.clearOptions();
            activity.setStatus(ActivityStatus.PENDING);

            for(int i = 0 ; i < options.size() ; i++){
                activity.addNewPayOption(options.get(i).getSelectionModel().getSelectedItem(), Integer.valueOf(hours.get(i).getText()));
            }

            ServiceHandler.setValues(ServiceID.STOREACTIVITYSERVICE,activity);
            ServiceHandler.startService(ServiceID.STOREACTIVITYSERVICE);
            AlertBoxLogIn.display("Alert", "Storing activity...");

        }
    }

    private class ActivityStoredHandler implements EventHandler<Event>{

        @Override
        public void handle(Event event) {
            if((boolean)ServiceHandler.getValues(ServiceID.STOREACTIVITYSERVICE))
                AlertBoxLogIn.display("Success", "Activity stored successfully!");
            else
                AlertBoxLogIn.display("Failed", "Storing activity failed!");
           
        }
    }



}