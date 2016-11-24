import org.apache.xpath.operations.Bool;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by mhahue on 11/22/2016.
 *
 * Comments
 *
 * //@FindBy(xpath = "//div[@class='left-corner']//ul[@class='action-links']/li/a")
 //@FindBy(xpath = "//a[@href='http://192.168.100.125/drupal-7.15/?q=admin/people/create']")

 * //((JavascriptExecutor)webDriver).executeScript("arguments[0].checked = true;", addUserButton);
 //addUserButton.click();
 //((JavascriptExecutor)webDriver).executeScript("arguments[0].checked = true;", usernameField// ;
 //webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 //Assert.assertTrue(usernameField.isDisplayed());
 //        WebElement submit = webDriver.findElement(By.xpath("//input[@id='edit-submit']"));
 //        Actions action = new Actions(webDriver);
 //        action.moveToElement(submit).build().perform();
 //        action.click(submit);
 //        usernameInput.click();
 //        usernameInput.sendKeys("safsadsa");
 //    public UserForm form() {
 //
 //        UserForm userForm = PageFactory.initElements(webDriver, UserForm.class);
 //        userForm.waitForPageToLoad();
 //        return userForm;
 //    }

 */
public class CreateUsers {

    private WebDriver webDriver;

    public CreateUsers(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(id = "toolbar-link-admin-people")
    private WebElement clickPeopleOption;

    @FindBy(xpath = "//div[@id='overlay-container']/iframe[@title='People dialog']")
    private WebElement iframeAddUser;

    @FindBy(xpath = "//div[@id='squeeze']/div/div/ul/li/a")
    private WebElement addUserButton;

    @FindBy(xpath = "//iframe[@class='overlay-element']")
    private WebElement iframePeople;

    @FindBy(css = "#edit-name")
    private WebElement usernameField;

    @FindBy(css = "#edit-mail")
    private WebElement emailField;

    @FindBy(css = "#edit-pass-pass1")
    private WebElement passwordField;

    @FindBy(css = "#edit-pass-pass2")
    private WebElement confirmPasswordField;

    @FindBy(css = "#edit-roles-3")
    private WebElement administratorCheck;

    @FindBy(css = "#edit-submit")
    private WebElement createNewAccount;

    //click on people and add user
    public void addUser() {

        clickPeopleOption.click();

        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(iframeAddUser);
        addUserButton.click();

        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(iframePeople);
    }

    //send keys to the form: username, pass, etc and create a new account
    public VerifyUser createUser(String username, String email, String password, Boolean checkbox)  {

        usernameField.click();
        usernameField.sendKeys(username);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);

        if(checkbox == true) {
            administratorCheck.click();
        }
        createNewAccount.click();

        //this is my next page
        VerifyUser verifyUser = PageFactory.initElements(webDriver, VerifyUser.class);
        verifyUser.waitForPageToLoad();
        return verifyUser;
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-name")));
    }
}
