package SEF_HR_APP.backend;

public class BackendHandler {

    private static BackendTaskListener taskListener;

    /**
     * Function for closing the task listener
     */
    public static void close(){
        taskListener.stop();
    }

    /**
     * Function for setting the task listener
     * @param l
     */
    public static void setTaskListener(BackendTaskListener l){
        taskListener = l;
    }

}