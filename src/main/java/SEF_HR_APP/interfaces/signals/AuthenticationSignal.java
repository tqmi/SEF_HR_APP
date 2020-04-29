package SEF_HR_APP.interfaces.signals;

import SEF_HR_APP.backend.AuthenticationService;
import SEF_HR_APP.interfaces.SignalHandler;

public class AuthenticationSignal implements Signal{

    private volatile boolean signal = false;
    private String uname,pass;

    public AuthenticationSignal(String uname, String pass){
        this.uname = uname;
        this.pass = pass;

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

        boolean ans = AuthenticationService.authenticate(uname, pass);

        SignalHandler.setSignalFrontend(new AuthenticationSignalResponse(ans));

    }


}