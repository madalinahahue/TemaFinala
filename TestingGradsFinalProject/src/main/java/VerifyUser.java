import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by mhahue on 11/24/2016.
 */
public class VerifyUser {

    private WebDriver webDriver;
    public VerifyUser(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//ul[@id='toolbar-user']/li[2]/a")
    private WebElement logOutButton;

    @FindBy(id = "edit-name")
    private WebElement usernameField;

    @FindBy(id = "edit-pass")
    private WebElement passwordField;

    @FindBy(id = "edit-submit")
    private WebElement submitButton;

    @FindBy(id = "toolbar-link-admin-people")
    private WebElement clickPeopleOption;

    @FindBy(xpath = "//form[@id='user-admin-account']/div/table[2]/tbody/tr")
    private List<WebElement> listofUsers;

    @FindBy(xpath = "//a[@class='username']")
    private List<WebElement> listOfUsernames;

    @FindBy(xpath = "//div[@class='item-list']")
    private List<WebElement> listOfRoles;

    @FindBy(xpath = "//div[@id='overlay-container']/iframe[@title='People dialog']")
    private WebElement iframeAddUser;

//    @FindBy(xpath = "//a[@href='/drupal-7.15/?q=user/22/contact']")
//    private WebElement clickOnContact;

    @FindBy(xpath = "//div[@id='tabs-wrapper']/ul/li[5]/a")
    private WebElement clickOnContact;

    @FindBy(id = "edit-name")
    private WebElement thisIsMyName;

    @FindBy(id = "toolbar")
    private List<WebElement> toolbarList;

    //login with the user i just created
    public void verifyLogin(String username, String password) {

        webDriver.switchTo().defaultContent();
        logOutButton.click();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();
        clickPeopleOption.click();
    }

    //search by my name in the list of usernames and print my status(admin or not)
    //when my name is found, click on it
    //click on contact option to see if my name is the same with the one i logged in

    public ExploringTheMenu verifyUsers(String username) {

        int toolbar = toolbarList.size();
        if (toolbar != 0) {

            clickPeopleOption.click();

            webDriver.switchTo().defaultContent();
            webDriver.switchTo().frame(iframeAddUser);

            for (int i = 0; i < listofUsers.size(); i++) {

                String users = listOfUsernames.get(i).getText();
                //System.out.println(users);
                String roles = listOfRoles.get(i).getText();
                //System.out.println(roles);

                if (users.contains(username)) {
                    System.out.println("The user " + users + " exists and has the role of: " + roles);
                    listOfUsernames.get(i).click();
                    break;
                }
            }

            webDriver.switchTo().defaultContent();
            clickOnContact.click();
        }
        else {
            System.out.println("This user is not Admin");
        }
        ExploringTheMenu exploringTheMenu = PageFactory.initElements(webDriver, ExploringTheMenu.class);
        exploringTheMenu.waitForPageToLoad();
        return exploringTheMenu;
    }


    //i printed my name to perform an assert and exercise
    public String displayContact() {

        thisIsMyName.click();
        String myName = thisIsMyName.getAttribute("value");
        //System.out.println(myName);
        return myName;
    }


    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
    }
}
