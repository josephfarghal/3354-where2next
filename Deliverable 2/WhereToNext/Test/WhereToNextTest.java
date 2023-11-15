import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.WeakHashMap;

public class WhereToNextTest extends TestCase {
    @Test
    public void main() {
        String name = "Lennon_Crow-bar";
        String pass = "Pr@gam3";
        int DOB = 23;

        User you = new User(name, pass, DOB);
       assertEquals(you , WhereToNext.makeNewUser());
    }
}