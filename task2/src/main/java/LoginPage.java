import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sun.applet.Main;

/**
 * Created by a.maley on 12.10.2016.
 */
public class LoginPage {
    private final WebDriver driver;
    private By loginFieldLocator = By.xpath("//*[@id='auth-container__forms']//input[@placeholder='Ник или e-mail']");
    private By passwordFieldLocator = By.xpath("//*[@id='auth-container__forms']//input[@placeholder='Пароль']");
    private By loginButtonLocator = By.xpath("//*[@id='auth-container__forms']//button[@class='auth-box__auth-submit auth__btn auth__btn--green']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private LoginPage typeUserName(String username){
        driver.findElement(loginFieldLocator).sendKeys(username);
        return this;
    }


    private LoginPage typePassword(String password){
        driver.findElement(passwordFieldLocator).sendKeys(password);
        return this;
    }

    private MainPage submitLogin(){
        driver.findElement(loginButtonLocator).submit();
        return new MainPage(driver);
    }

    public MainPage loginAs(String login, String password){
        typeUserName(login);
        typePassword(password);
        return submitLogin();

    }
}
