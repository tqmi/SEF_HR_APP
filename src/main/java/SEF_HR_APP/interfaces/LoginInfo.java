package SEF_HR_APP.interfaces;

import SEF_HR_APP.backend.security.Hasher;

public class LoginInfo {

    

    private String username;
    private String password;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param username
     * @param password
     */
    public LoginInfo(String username, String password) {

        this.username = username;
        this.password = Hasher.getSHA256(username+password);
    }

    
    
}