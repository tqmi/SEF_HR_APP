package SEF_HR_APP.backend;

import java.util.ArrayList;

import SEF_HR_APP.backend.services.AuthenticationService;
import javafx.event.Event;
import javafx.event.EventHandler;

public class ServiceHandler {

    private static ArrayList<ServiceTemplate> services;

    public static void initialize(){
        services = new ArrayList<>();
        services.add(new AuthenticationService());
    }

    
    public enum ServiceID{
        AUTHENTICATIONSERVICE(0);

        private int index;

        private ServiceID(int index){
            this.index = index;
        }

        public int getIndex(){
            return index;
        }
    }

    public static void startService(ServiceID id){
        ServiceTemplate s = services.get(id.getIndex());
        s.reset();
        s.start();
    }

    public static void stopService(ServiceID id){
        services.get(id.getIndex()).cancel();
    }

    public static void setOnSucceededHandler(ServiceID id,EventHandler<Event> handler){
        services.get(id.getIndex()).setOnSucceeded(handler);
    }

    public static <T> T getValues(ServiceID id){
        
        return (T)services.get(id.getIndex()).getResponse();
        
    }

    public static <T> void setValues(ServiceID id, T data){
        services.get(id.getIndex()).setValues(data);
    }


}