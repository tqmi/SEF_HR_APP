package SEF_HR_APP.interfaces.signals;

import SEF_HR_APP.backend.BackendHandler;

public class ApplicationClosingSignal implements Signal {

    private volatile boolean signal = false;

    @Override
    public void setSignal() {
        signal = true;
    }

    @Override
    public boolean readSignal() {
        boolean temp = signal;
        signal = false;
        return temp;
    }

    @Override
    public void handleSignal(){

        BackendHandler.close();

    }

    

}