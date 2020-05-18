package SEF_HR_APP.backend.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import SEF_HR_APP.backend.datamodels.user.AccountType;
import SEF_HR_APP.backend.datamodels.user.Position;
import SEF_HR_APP.backend.datamodels.user.Seniority;
import SEF_HR_APP.backend.datamodels.user.User;
import SEF_HR_APP.interfaces.LoginInfo;

public class DBHandlerTest {
    
    @BeforeClass
    public static void connectToDB(){
        DBHandler.connectDB();
    }

    @AfterClass
    public static void closeDB(){
        DBHandler.close();
    }

    @Test
    public void adminShouldLogIn(){

        LoginInfo testInfo= new LoginInfo("admin", "admin");
        User testUser = DBHandler.findUser(testInfo.getUsername(), testInfo.getPassword());

        assertNotNull("Admin account should be found! Verify for admin.stp to not be configured!", testUser);

        assertEquals("admin", testUser.getUsername());

    }

    @Test
    public void adminShouldBeFound(){
        assertTrue("isUsernameUsed should return true for admin",DBHandler.isUsernameUsed("admin"));
    }


    @Test
    public void newUserShouldBeInsertedCorrectly(){

        User newU = new User("test", Position.ADMIN, "test", Seniority.JUNIOR, 0.0, 0, AccountType.ADMIN);

        String name = "test";

        int i = 1;
        while(DBHandler.isUsernameUsed(name+i)) i++;

        newU.setUsername(name+i);
        newU.setPassword("test");

        assertTrue("User should be inserted into table", DBHandler.insertUserIntoTable(newU));
        assertFalse("Same user should not be entered twice", DBHandler.insertUserIntoTable(newU));

        assertEquals(newU.getUsername(), DBHandler.getUser(newU.getUsername()).getUsername());


    }



}