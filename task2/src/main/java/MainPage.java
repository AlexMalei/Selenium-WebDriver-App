import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by a.maley on 12.10.2016.
 */
public class MainPage {
    private final WebDriver driver;
    private String entryBtnText = "Вход";
    private String quitElemText = "Выйти";
    private By entryButtonLocator = By.xpath(String.format("//*[@id='userbar']//*[contains(text(),%s)]", entryBtnText));
    private By quitElementLocator = By.xpath(String.format("//*[@id='userbar']//a[contains(text(), %s)]", quitElemText));
    //private Ele

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickLoginButton(){
        driver.findElement(entryButtonLocator).click();
        return new LoginPage(driver);
    }

   // public boolean



}
