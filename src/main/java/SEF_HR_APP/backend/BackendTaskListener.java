package SEF_HR_APP.backend;

import SEF_HR_APP.interfaces.SignalHandler;

public class BackendTaskListener implements Runnable{

    private volatile boolean running = false;
    private Thread runningThread;

    /**
     * Main loop for backend thread
     */
    @Override
    public void run() {

        running = true;
        //Get running thread
        runningThread = Thread.currentThread();
        
        System.out.println("Running on thread " + runningThread.getId());

        while(running){
            //Check if new signals have been received
            SignalHandler.readSignals();
            
        }

        runningThread = null;

        System.out.println("Backend stoped!");
    }


    /**
     * Function called for stopping backend tasks
     */
    public void stop(){
        System.out.println("Backend shutting down!");
        running = false;
        runningThread.interrupt();
    }

}