package SEF_HR_APP.backend;

public class AuthenticationService {

    public static boolean authenticate(String uname, String pass){

        if(uname.equals("admin") && pass.equals("notAdmin"))
            return true;

        return false;
    }

}