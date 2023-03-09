package FontSide;

import java.io.Serializable;

/**
 * Represents a user who uses application
 * @author Imperfecton
 */

public class User implements Serializable {

    private String userLastName;
    private String userFirstName;
    private String password;
    private String userEmail;
    private String userPhone;
    private String status;

    
    
    public User(String userLastName, String userFirstName, String userEmail, String userPhone, String password, String status) {
        this.userLastName = userLastName;
        this.userFirstName = userFirstName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.password = password;
        this.status = status;
    }
    
    public String getPassword(){
        return password;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getStatus() {
        return status;
    }
}