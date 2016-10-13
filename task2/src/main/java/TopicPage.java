import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class TopicPage {
    private WebDriver driver;
    By topicLocator = By.xpath("//div[@class='schema-header']//h1");

    public TopicPage(WebDriver driver) {
        this.driver = driver;
    }

    public void returnMainPage(){
        driver.get(TestUtil.webSite);
    }

    public String getTopicOnPageAndReturnMain() {
        driver.manage().timeouts().implicitlyWait(TestUtil.pageTimeout, TimeUnit.SECONDS);
        String topic = driver.findElement(topicLocator).getText();
        returnMainPage();
        return topic;
    }
}
