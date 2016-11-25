import com.gargoylesoftware.htmlunit.Page;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by mhahue on 11/22/2016.
 * Code
 * //((JavascriptExecutor)webDriver).executeScript("arguments[0].checked = true;", createUsers.addUser());
 */
public class ClassTest {

    private WebDriver webDriver;
    private HomePage test2Params;
    private CreateUsers createUsers;
    private VerifyUser verifyUser;
    private ParamDatabase paramDatabase;


    @Before
    public void before() {
        webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void after() throws InterruptedException {
        Thread.sleep(5000);
        webDriver.close();
    }

    @Test
    public void test() throws  ClassNotFoundException, SQLException {

        test2Params = PageFactory.initElements(webDriver, HomePage.class);
        test2Params.loginPage("admin", "admin_pass");
        //verificare text "Hello admin"
        String textIsPresent = test2Params.verifyLogin();
        Assert.assertEquals("Hello admin", textIsPresent);
        System.out.println("Assert is working --> " + textIsPresent);

        createUsers = PageFactory.initElements(webDriver, CreateUsers.class);
        createUsers.addUser();
        createUsers.createUser("madalinaAdmin", "madalina_admin@test.com", "testtest", true); //admin
        createUsers.createUser("madalinaUser", "madalina_user@test.com", "testtest", false); //user

        verifyUser = PageFactory.initElements(webDriver, VerifyUser.class);
        verifyUser.verifyLogin("madalinaAdmin", "testtest");
        verifyUser.verifyUsers("madalinaAdmin");


        String displayContact = verifyUser.displayContact();
        Assert.assertEquals("madalinaAdmin", displayContact);
        System.out.println("My name is:" + displayContact);

        System.out.println("PARAM DATABASE");

        paramDatabase = PageFactory.initElements(webDriver, ParamDatabase.class);
        paramDatabase.retrieveUser("madalina", "madalina");

    }
}
