package SEF_HR_APP.interfaces;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import SEF_HR_APP.interfaces.signals.Signal;

public class SignalHandler {

    private static volatile ConcurrentLinkedQueue<Signal> signalQueue = new ConcurrentLinkedQueue<>();

    /**
     * Send a signal to backend for processing
     * @param s
     */
    public static void setSignal(Signal s) {
        synchronized (signalQueue) {
            s.setSignal();
            signalQueue.add(s);
            signalQueue.notify();
        }
    }

    /**
     * 
     */
    public static void readSignals() {
        int size;
        ArrayList<Signal> signals = new ArrayList<>();

        synchronized (signalQueue) {

            while (signalQueue.isEmpty()) {
                try {
                    signalQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }

            size = signalQueue.size();
            while(!signalQueue.isEmpty()){
                signals.add(signalQueue.remove());
            }

        }

        for(int i = 0 ; i < size ; i++){
            Signal sig = signals.get(i);
            if(sig.readSignal()){
                sig.handleSignal();
            }
        }
        

    }



}