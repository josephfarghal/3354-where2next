import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.assertThrows;

//Due to error handling in the program all of these test cases will pass. Failed tests can be identified by
//the message that is printed to the terminal

public class WhereToNextTest extends TestCase {

    //Should any one of the test below fail for whatever reason assume that the user cannot create/access their account

    //A test of constructor to show that object can be created
    @Test
    public void testMainWithName() {
        String name = "Lennon_Crow";
        String pass = "Pr0gr@m";
        int age = 27;
        User test = new User(name, pass, age);
    }

    //Below lines 19 - 55 represent what occurs inside makeNewUser() (Could not be tested on automatically because it
    // requires user input and I could not figure out how to add pre-typed statements as proxy user input).
    //Example of a test of Age that should pass
    @Test
    public void testMainVerifyAgeCorrect() {
        String DOB = "12_2_2001";
        WhereToNext.verifyAge(DOB);
    }

    //Example of a test of Age that should fail & therefore print a custom warning (Test should Pass)
    @Test
    public void testMainVerifyAgeIncorrect() {
        String DOB = "12_2_2024";
        WhereToNext.verifyAge(DOB);
    }

    //Example of a test that should pass
    @Test
    public void testMainVerifyNameCorrect(){
        String name = "Lennon_Crow-bar";
        WhereToNext.verifyName(name);
    }

    //Example of a test that should fail & therefore print a message to terminal (Test should pass)
    @Test
    public void testMainVerifyNameIncorrect(){
        String name = "Ariel_PleaseReadTowerOfGod";
        WhereToNext.verifyName(name);
    }

    //Example of a test that should fail & therefore print a message to terminal (Test should pass)
    @Test
    public void testMainVerifyPass(){
        String pass = "Unacceptable!";
        WhereToNext.verifyPass(pass);
    }
}