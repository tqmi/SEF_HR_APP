package SEF_HR_APP.interfaces;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import SEF_HR_APP.interfaces.signals.Signal;

public class SignalHandler {

    private static volatile ConcurrentLinkedQueue<Signal> signalQueueBackend = new ConcurrentLinkedQueue<>();
    private static volatile ConcurrentLinkedQueue<Signal> signalQueueFrontend = new ConcurrentLinkedQueue<>();

    /**
     * Send a signal to backend for processing
     * @param s
     */
    public static void setSignalBackend(Signal s) {
        synchronized (signalQueueBackend) {
            s.setSignal();
            signalQueueBackend.add(s);
            signalQueueBackend.notify();
        }
    }

    /**
     * 
     */
    public static void readSignalsBackend() {
        int size;
        ArrayList<Signal> signals = new ArrayList<>();

        synchronized (signalQueueBackend) {

            while (signalQueueBackend.isEmpty()) {
                try {
                    signalQueueBackend.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }

            size = signalQueueBackend.size();
            while(!signalQueueBackend.isEmpty()){
                signals.add(signalQueueBackend.remove());
            }

        }

        for(int i = 0 ; i < size ; i++){
            Signal sig = signals.get(i);
            if(sig.readSignal()){
                sig.handleSignal();
            }
        }
        

    }


    /**
     * Send a signal to frontend for processing
     * @param s
     */
    public static void setSignalFrontend(Signal s) {
        synchronized (signalQueueFrontend) {
            s.setSignal();
            signalQueueFrontend.add(s);
            signalQueueFrontend.notify();
        }
    }

    /**
     * 
     */
    public static Signal readSignalsFrontend() {

        Signal sigReceived;

        synchronized (signalQueueFrontend) {

            while (signalQueueFrontend.isEmpty()) {
                try {
                    signalQueueFrontend.wait(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            if(signalQueueFrontend.isEmpty()){
                return null;
            }
            sigReceived = signalQueueFrontend.remove();
        }

        if(sigReceived.readSignal())
            return sigReceived;

        return null;
        

    }



}