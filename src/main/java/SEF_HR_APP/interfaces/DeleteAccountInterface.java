package SEF_HR_APP.interfaces;

public class DeleteAccountInterface {

    private String username;

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
     * @param username
     */
    public DeleteAccountInterface(String username) {
        this.username = username;
    }
    
    
}