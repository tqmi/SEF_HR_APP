package SEF_HR_APP.interfaces.signals;

public class AuthenticationSignalResponse implements Signal{
    
    private volatile boolean signal = false;
    private boolean authenticationResult;

    public AuthenticationSignalResponse(boolean authenticationResult){
        this.authenticationResult = authenticationResult;
    }

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
    public void handleSignal() {
    }

    public boolean getAuthenticationResult(){
        return authenticationResult;
    }

}