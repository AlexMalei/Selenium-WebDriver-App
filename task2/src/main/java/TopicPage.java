import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class TopicPage {
    private WebDriver driver;
    By topicLocator = By.xpath("//div[@class='schema-header']//h1");

    public TopicPage(WebDriver driver) {
        this.driver = driver;
    }

  /*  public MainPage returnMainPage(){
        driver.get(TestUtil.webSite);
        return new MainPage(driver);
    }*/

    public String getTopicOnPage() {
        driver.manage().timeouts().implicitlyWait(TestUtil.pageTimeout, TimeUnit.SECONDS);
        return driver.findElement(topicLocator).getText();
    }
}
