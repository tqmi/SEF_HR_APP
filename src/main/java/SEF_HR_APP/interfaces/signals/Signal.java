package SEF_HR_APP.interfaces.signals;

public interface Signal {

    /**
     * Function used for setting signal status
     */
    public void setSignal();

    /**
     * Function used for retrieving signal status 
     */
    public boolean readSignal();

    /**
     * Function used for implementing signal functionality
     */
    public void handleSignal();

}