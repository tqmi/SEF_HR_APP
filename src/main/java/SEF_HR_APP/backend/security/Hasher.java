package SEF_HR_APP.backend.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Hasher {

    public static String getSHA256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
    
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
    
            return hexString.toString();
        } catch(Exception ex){
           throw new RuntimeException(ex);
        }
    }
}