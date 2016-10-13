import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

/**
 * Created by a.maley on 12.10.2016.
 */
public class MainPage {
    private final WebDriver driver;

    private final String entryBtnString = "'Вход '";
    private final String quitElemString = "'Выйти'";
    private final String patterPopularTopics = "//div[@id='container']//ul[@class='project-navigation__list project-navigation__list_secondary']/li";
    private final String patternUserBarLocator = "//*[@id='userbar']//*[contains(text(),%s)]";

    private final By entryButtonLocator = By.xpath(String.format(patternUserBarLocator, entryBtnString));
    private final By quitElementLocator = By.xpath(String.format(patternUserBarLocator, quitElemString));
    private final By mainPopularTopicsLocator = By.xpath(patterPopularTopics);

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
        try {
            driver.findElement(quitElementLocator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private List<WebElement> getMainCategories(){
        List<WebElement> listElements = driver.findElements(mainPopularTopicsLocator);
        return listElements;
    }


    private WebElement getRandomTopicElement(){
        Random random = new Random(47);
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

    public void manageNews() {
        String pageSourse = driver.getPageSource();
        
    }
}
