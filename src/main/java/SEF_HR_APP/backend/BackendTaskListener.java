package SEF_HR_APP.backend;

import SEF_HR_APP.interfaces.SignalHandler;

public class BackendTaskListener implements Runnable{

    private volatile boolean running = false;
    private Thread runningThread;

    @Override
    public void run() {

        running = true;
        runningThread = Thread.currentThread();
        
        System.out.println("Running on thread " + runningThread.getId());

        while(running){

            SignalHandler.readSignals();
            
        }

        runningThread = null;

        System.out.println("Backend stoped!");
    }


    public void stop(){
        System.out.println("Backend shutting down!");
        running = false;
        runningThread.interrupt();
    }

}