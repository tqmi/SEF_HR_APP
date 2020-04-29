package SEF_HR_APP.backend;

public class BackendHandler {

    private static BackendTaskListener taskListener;


    public static void close(){
        taskListener.stop();
    }


    public static void setTaskListener(BackendTaskListener l){
        taskListener = l;
    }

}