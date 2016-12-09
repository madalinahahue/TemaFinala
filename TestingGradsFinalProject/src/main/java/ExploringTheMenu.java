import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;

/**
 * Created by mhahue on 11/29/2016.
 */
public class ExploringTheMenu {
    private WebDriver webDriver;

    public ExploringTheMenu(WebDriver webDriver) {
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

    @FindBy(xpath = "//input[@id='edit-submit']")
    private WebElement saveButton;

    public void additionalTests(String addTitle) {
        addContentField.click();

        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(iframeAddArticle);
        addArticle.click();


        webDriver.switchTo().defaultContent();
        webDriver.switchTo().frame(iframeAddTitleText);
        addTitleText.sendKeys(addTitle);

        //addBodyText.sendKeys(addBody);
    }



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

    public void additionalTests1() {
        selectButton.click();
        Select oSelect = new Select(textFormat);
        oSelect.selectByIndex(2);

        StringSelection ss = new StringSelection("C:\\Automation\\download.jpg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        Robot robot = null;

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
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


        menuList.click();
        closeButton.click();
        saveButton.click();

    }



    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
    }
}
