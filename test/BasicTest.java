import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {
   
    @Test
    public void createAndRetrieveUser()
    {
    	new User("dungth@hpu.edu.vn", "secret","Rev").save();
    	// retrieve
    	User rev = User.find("byEmail", "dungth@hpu.edu.vn").first();
    	assertNotNull(rev);
    	assertEquals("Rev", rev.fullname);
    	
    }
    @Test
    public void tryConnectAsUser() {
        // Create a new user and save it
        new User("bob@gmail.com", "secret", "Bob").save();
        
        // Test 
        assertNotNull(User.connect("bob@gmail.com", "secret"));
        assertNull(User.connect("bob@gmail.com", "badpassword"));
        assertNull(User.connect("tom@gmail.com", "secret"));
    }
    
}
