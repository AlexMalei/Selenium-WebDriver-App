import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by a.maley on 12.10.2016.
 */
public class MainPage {
    private final WebDriver driver;
    private By entryButtonLocator = By.xpath("//*[@id='userbar']//*[contains(text(),'Вход ')]");

    //private Ele

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickLoginButton(){
        driver.findElement(entryButtonLocator).click();
        return new LoginPage(driver);
    }


}
