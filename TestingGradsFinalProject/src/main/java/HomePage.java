import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mhahue on 11/22/2016.
 */
public class HomePage {

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriver.get("http://192.168.100.125/drupal-7.15/");
    }

    @FindBy(id = "edit-name")
    private WebElement usernameField;

    @FindBy(id = "edit-pass")
    private WebElement passwordField;

    @FindBy(id = "edit-submit")
    private WebElement submitButton;

    @FindBy(xpath = "//a[@title='User account']")
    private WebElement helloUser;

    //login, password, submit
    public CreateUsers loginPage(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();

        //return next page
        CreateUsers createUsers = PageFactory.initElements(webDriver, CreateUsers.class);
        createUsers.waitForPageToLoad();
        return createUsers;
    }

    //verify with an assert my name
    public String verifyLogin() {
        String textIsPresent = helloUser.getText();
        //System.out.println(textIsPresent);
        return textIsPresent;
    }




}
