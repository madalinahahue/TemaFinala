import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by mhahue on 11/29/2016.
 * **************************Add content and delete the content************************************
 */
public class AddDeleteContent {
    private WebDriver webDriver;

    public AddDeleteContent(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//div[@id='toolbar']//li[@class='first leaf']//a")
    private WebElement addContentField;

    @FindBy(xpath = "//div[@id='overlay-container']/iframe[@title='Add content dialog']")
    private WebElement iframeAddArticle;

    @FindBy(xpath = "//div[@id='block-system-main']/div/dl/dt[1]/a")
    private WebElement addArticle;

    @FindBy(xpath = "//div[@id='overlay-container']/iframe[@title='Create Article dialog']")
    private WebElement iframeAddTitleText;

    @FindBy(xpath = "//input[@id='edit-title']")
    private WebElement addTitleText;

    @FindBy(xpath = "//textarea[@id='edit-body-und-0-value']")
    private WebElement addBodyText;

    @FindBy(xpath = "//select[@id='edit-body-und-0-format--2']")
    private WebElement textFormat;

    @FindBy(xpath = "//input[@class='form-file']")
    private WebElement selectButton;

    @FindBy(xpath = "//form[@id='article-node-form']//ul[@class='vertical-tabs-list']//li[5]")
    private WebElement menuList;

    @FindBy(xpath = "//input[@id='edit-comment-1']")
    private WebElement closeButton;

    @FindBy(xpath = "//select[@id='edit-operation']/option[@value='delete']")
    private WebElement deleteSelectedContent;

    @FindBy(id = "edit-submit")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@id='toolbar']//li[@class='last leaf']//a")
    private WebElement findContentButton;

    @FindBy(xpath = "//div[@class='fieldset-wrapper']/input[@type='submit']")
    private WebElement updateButton;

    @FindBy(xpath = "//div[@id='overlay-container']/iframe[@title='Content dialog']")
    private WebElement iframeFindContent;

    @FindBy(xpath = ".//*[@id='node-admin-content']/div/table[2]/thead/tr/th[1]/input")
    private WebElement checkBox;

    @FindBy(id = "edit-submit")
    private WebElement deleteItems;

    public void AddContent(String addTitle) {
        addContentField.click();

        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(iframeAddArticle);
        addArticle.click();


        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(iframeAddTitleText);
        addTitleText.sendKeys(addTitle);

        //addBodyText.sendKeys(addBody);
    }

    //import Body from .txt
    public void importFromFile() throws IOException {
        //Create File In D: Driver.
        String TestFile = "C:\\Automation\\textBody.txt";
        File FC = new File(TestFile);//Created object of java File class.
        FC.createNewFile();//Create file.

        //Reading from file.
        //Create Object of java FileReader and BufferedReader class.
        FileReader FR = new FileReader(TestFile);
        BufferedReader BR = new BufferedReader(FR);
        String Content = "";

        //Loop to read all lines one by one from file and print It.
        while ((Content = BR.readLine()) != null) {
            //System.out.println(Content);
            addBodyText.sendKeys(Content);
        }
    }


    public void AddContent2() {

        selectButton.click();
        Select oSelect = new Select(textFormat);
        oSelect.selectByIndex(2);

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        StringSelection ss = new StringSelection("C:\\Automation\\download.jpg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        Robot robot = null;

        //select picture

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }


        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_CONTROL);

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        menuList.click();
        closeButton.click();
        saveButton.click();
    }

    public EditStructure findContent() {
        webDriver.switchTo().defaultContent();
        findContentButton.click();

        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(iframeFindContent);

        checkBox.click();
        deleteSelectedContent.click();

        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(iframeFindContent);
        updateButton.click();
        //delete all articles
        deleteItems.click();

        EditStructure editStructure = PageFactory.initElements(webDriver, EditStructure.class);
        editStructure.waitForPageToLoad();
        return editStructure;
    }



    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
    }
}
