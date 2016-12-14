import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by mhahue on 12/13/2016.
 */
public class EditStructure {
    private WebDriver webDriver;

    public EditStructure(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(id = "toolbar-link-admin-structure")
    private WebElement structureClick;

    @FindBy(xpath = "//div[@id='overlay-container']/iframe[@title='Structure dialog']")
    private WebElement iframeAddStructure;

    @FindBy(xpath = "//dl[@class='admin-list']/dt[2]/a")
    private WebElement contactForm;

    @FindBy(xpath = "//div[@id='overlay-container']/iframe[@class='overlay-element']")
    private WebElement iframeAddCategory;

    @FindBy(xpath = "//div[@id='squeeze']/div/div/ul[@class='action-links']/li/a")
    private WebElement addCategory;

    @FindBy(id = "edit-category")
    private WebElement categoryField;

    @FindBy(id = "edit-recipients")
    private WebElement recipientsField;

    @FindBy(xpath = "//select[@id='edit-weight']/option[15]")
    private WebElement weightField;

    @FindBy(id = "edit-submit")
    private WebElement saveButton;



    public void addStructure(String category, String recipients) {

        webDriver.switchTo().defaultContent();
        structureClick.click();

        webDriver.switchTo().frame(iframeAddStructure);
        contactForm.click();

        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(iframeAddCategory);
        addCategory.click();

        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(iframeAddCategory);
        categoryField.sendKeys(category);


        recipientsField.sendKeys(recipients);
        weightField.click();
        saveButton.click();
    }


    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
    }
}
