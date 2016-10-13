
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainPage {
    private final WebDriver driver;

    private static String entryBtnString = "'Вход '";
    private static String quitElemString = "'Выйти'";
    private static String patterPopularTopics = "//div[@id='container']//ul[@class='project-navigation__list project-navigation__list_secondary']/li";
    private static String patternUserBarLocator = "//*[@id='userbar']//*[contains(text(),%s)]";
    private static String lineSeparator = " \n";


    private final By entryButtonLocator = By.xpath(String.format(patternUserBarLocator, entryBtnString));
    private final By quitElementLocator = By.xpath(String.format(patternUserBarLocator, quitElemString));
    private final By mainPopularTopicsLocator = By.xpath(patterPopularTopics);
    private final By nicknameLocator = By.xpath(".//*[@id='userbar']//*[@class='user-name']/*[contains(@data-bind,'html:')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickLoginButton(){
        WebElement btnLogin = new WebDriverWait(driver, TestUtil.pageTimeout).until(new ExpectedCondition<WebElement>(){
            public WebElement apply(WebDriver d) {
                return d.findElement(entryButtonLocator);
            }});
        btnLogin.click();
        return new LoginPage(driver);
    }


    public MainPage logOut(){
        WebElement quitElement = driver.findElement(quitElementLocator);
        quitElement.click();
        return new MainPage(driver);
    }

    public boolean isAuthorized(){
        new WebDriverWait(driver, TestUtil.pageTimeout).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
        WebElement currentElement = driver.findElement(nicknameLocator);
        if (currentElement.isDisplayed()){
            return true;
        }
        else
            return false;
    }

    private List<WebElement> getMainCategories(){
        List<WebElement> listElements = driver.findElements(mainPopularTopicsLocator);
        return listElements;
    }

    private WebElement getRandomTopicElement(){
        Random random = new Random();
        List<WebElement> topicElementList = getMainCategories();
        return topicElementList.get(random.nextInt(topicElementList.size()));
    }

    public String getRandomTopic() {
        return getRandomTopicElement().getText();
    }

    public TopicPage goRandomTopic(String topic) {
        driver.findElement(By.xpath(patterPopularTopics + String.format("//*[contains(text(),'%s')]", topic))).click();
        return new TopicPage(driver);
    }

    private List<String> extractNews(){
        String pageSourse = driver.getPageSource();
        Pattern pattern = Pattern.compile("class=\"text-i\">([\\s\\S]*?)<");
        Matcher matcher = pattern.matcher(pageSourse);
        List<String> listMatches = new ArrayList<String>();

        while (matcher.find()){
            listMatches.add(matcher.group(1));
        }
        return listMatches;
    }

    public void manageNews() {
        try {
            writeNewsCsvFile(extractNews());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void writeNewsCsvFile(List<String> allNews) throws IOException {
        File file = new File(TestUtil.csvFilePath);
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        for (String news : allNews){
            fileWriter.write(news);
            fileWriter.write(lineSeparator);
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public String getTitle() {
        driver.manage().timeouts().pageLoadTimeout(TestUtil.pageTimeout, TimeUnit.SECONDS);
        return driver.getTitle();
    }
}
