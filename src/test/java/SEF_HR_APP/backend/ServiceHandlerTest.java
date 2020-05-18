package SEF_HR_APP.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CompletableFuture;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.framework.junit.TestFXRule;

import SEF_HR_APP.backend.ServiceHandler.ServiceID;
import SEF_HR_APP.backend.database.DBHandler;
import SEF_HR_APP.backend.datamodels.activity.ActivityInformation;
import SEF_HR_APP.backend.datamodels.activity.MonthType;
import SEF_HR_APP.backend.datamodels.payoption.PayOption;
import SEF_HR_APP.backend.datamodels.user.AccountType;
import SEF_HR_APP.backend.datamodels.user.Position;
import SEF_HR_APP.backend.datamodels.user.Seniority;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.backend.mail.MailService;
import SEF_HR_APP.interfaces.DeleteAccountInterface;
import SEF_HR_APP.interfaces.LoginInfo;
import SEF_HR_APP.interfaces.RetrieveActivityInfo;
import SEF_HR_APP.interfaces.SetActivityStatus;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceHandlerTest extends ApplicationTest {

    @Rule
    public TestFXRule javafxRule = new TestFXRule();

    CompletableFuture<String> ft;

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }

    @BeforeClass
    public static void connectDB() {
        DBHandler.connectDB();
        MailService.initialize();
    }

    @Test
    public void test01Authentication() {

        ft = new CompletableFuture<>();

        ServiceID testService = ServiceID.AUTHENTICATIONSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertTrue(ServiceHandler.getValues(testService));
                

            }
        });

        ServiceHandler.setValues(testService, new LoginInfo("admin", "admin"));
        ServiceHandler.startService(testService);

        ft.join();

    }

    @Test
    public void test02CreateAccount() {


        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.CREATEACCOUNTSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertTrue(ServiceHandler.getValues(testService));
                
            }
        });

        

        ServiceHandler.setValues(testService,new User("test test", Position.ADMIN, "test@test", Seniority.JUNIOR, 0, 0, AccountType.ADMIN));
        ServiceHandler.startService(testService);
        
        ft.join();
    }

    
    
    @Test
    public void test03DeleteAccount() {
        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.DELETEACCOUNTSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertTrue(ServiceHandler.getValues(testService));
                
            }
        });


        ServiceHandler.setValues(testService, new DeleteAccountInterface("admin"));
        ServiceHandler.startService(testService);
        
        ft.join();
    }

  

    @Test
    public void test04RestoreAccount() {
        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.RESTOREACCOUNTSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertTrue(ServiceHandler.getValues(testService));
                
            }
        });


        ServiceHandler.setValues(testService,  new DeleteAccountInterface("admin"));
        ServiceHandler.startService(testService);
        
        ft.join();
    }

    @Test
    public void test05UpdateUser() {
        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.UPDATEUSERSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertTrue(ServiceHandler.getValues(testService));
                
            }
        });

        User nU = new User("test2", Position.ADMIN, "test@test", Seniority.JUNIOR, 0, 0, AccountType.ADMIN);
        nU.setUsername("testte1");

        ServiceHandler.setValues(testService, nU);
        ServiceHandler.startService(testService);
       
        ft.join();
    }

    @Test
    public void test06RetrieveUser() {
        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.RETRIEVEUSERSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertEquals(((User)ServiceHandler.getValues(testService)).getName(),"test2");
                
            }
        });


        ServiceHandler.setValues(testService, "testte1");
        ServiceHandler.startService(testService);
        
        ft.join();
    }

    @Test
    public void test07CreatePayOption() {
        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.CREATEPAYOPTIONSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertTrue(ServiceHandler.getValues(testService));
                
            }
        });


        ServiceHandler.setValues(testService, new PayOption("test", 100.0, "basis"));
        ServiceHandler.startService(testService);
        
        ft.join();
    }

    @Test
    public void test08UpdatePayOption() {
        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.UPDATEPAYOPTIONSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertTrue(ServiceHandler.getValues(testService));
                
            }
        });


        ServiceHandler.setValues(testService,new PayOption("test", 99.0, "basis"));
        ServiceHandler.startService(testService);
        
        ft.join();
    }


    @Test
    public void test09RetrievePayOption() {
        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.RETRIEVEPAYOPTIONSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertEquals(((PayOption)ServiceHandler.getValues(testService)).getPercentage(),99.0,0.01);
                
            }
        });


        ServiceHandler.setValues(testService,"test");
        ServiceHandler.startService(testService);
        
        ft.join();
    }

    @Test
    public void test10DeletePayOption() {
        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.DELETEPAYOPTIONSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertTrue(ServiceHandler.getValues(testService));
                
            }
        });


        ServiceHandler.setValues(testService, "test");
        ServiceHandler.startService(testService);
        
        ft.join();
    }

    

    @Test
    public void test11StoreActivity() {
        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.STOREACTIVITYSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertTrue(ServiceHandler.getValues(testService));
                
            }
        });


        ServiceHandler.setValues(testService, new ActivityInformation(MonthType.MAY));
        ServiceHandler.startService(testService);
        
        ft.join();
    }

  
    @Test
    public void test12UpdateReviewStatus() {
        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.UPDATEREVIEWSTATUSSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertTrue(ServiceHandler.getValues(testService));
                
            }
        });


        ServiceHandler.setValues(testService,new SetActivityStatus(new ActivityInformation(MonthType.MAY), User.getCurrentUser()));
        ServiceHandler.startService(testService);
        
        ft.join();
    }

    @Test
    public void test13RetrieveActivity() {
        ft = new CompletableFuture<>();
        ServiceID testService = ServiceID.RETRIEVEACTIVITYSERVICE;

        ServiceHandler.setOnSucceededHandler(testService, new EventHandler<Event>(){
        
            @Override
            public void handle(Event event) {
                ft.complete(null);
                assertNotNull(ServiceHandler.getValues(testService));
                
            }
        });


        ServiceHandler.setValues(testService, new RetrieveActivityInfo(null, MonthType.MAY));
        ServiceHandler.startService(testService);
        
        ft.join();
    }

    
}