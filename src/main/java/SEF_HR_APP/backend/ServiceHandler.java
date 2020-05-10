package SEF_HR_APP.backend;

import SEF_HR_APP.backend.services.*;
import javafx.event.Event;
import javafx.event.EventHandler;

public class ServiceHandler {

    /**
     * Used for identifying available services and initializing them
     */
    public enum ServiceID{
        AUTHENTICATIONSERVICE(new AuthenticationService()),
        CREATEACCOUNTSERVICE(new CreateAccountService()),
        CREATEPAYOPTIONSERVICE(new CreatePayOptionService()),
        STOREACTIVITYSERVICE(new StoreActivityService()),
        RETRIEVEACTIVITYSERVICE(new RetrieveActivityService());

        private ServiceTemplate service;

        private ServiceID(ServiceTemplate service){
            this.service = service;
        }
       
        public ServiceTemplate getService(){
            return service;
        }
    }

    /**
     * Starts the service identified by id
     * @param id the service to be started
     */
    public static void startService(ServiceID id){
        id.getService().reset();
        id.getService().start();
    }

    /**
     * Stops the service identified by id
     * @param id the service to be stoped
     */
    public static void stopService(ServiceID id){
        id.getService().cancel();
    }

    /**
     * Sets an onSucceededHandler to the service identified by id
     * @param id
     * @param handler
     */
    public static void setOnSucceededHandler(ServiceID id,EventHandler<Event> handler){
        id.getService().setOnSucceeded(handler);
    }
    /**
     * Returns the values generated byu the service
     * @param <T>
     * @param id
     * @return
     */
    public static <T> T getValues(ServiceID id){
        return (T) id.getService().getResponse();
    }

    /**
     * Sets the values for the service
     * @param <T>
     * @param id
     * @param data
     */
    public static <T> void setValues(ServiceID id, T data){
        id.getService().setValues(data);
    }


}