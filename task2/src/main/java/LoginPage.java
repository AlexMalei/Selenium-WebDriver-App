import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.applet.Main;

/**
 * Created by a.maley on 12.10.2016.
 */
public class LoginPage {
    private final WebDriver driver;
    private String loginPlaceholder = "Ник или e-mail";
    private String passwordPlaceholder = "Пароль";

    private By loginFieldLocator = By.xpath(String.format("//*[@id='auth-container__forms']//input[@placeholder=%s]", loginPlaceholder));
    private By passwordFieldLocator = By.xpath(String.format("//*[@id='auth-container__forms']//input[@placeholder=%s]", passwordPlaceholder));
    private By loginButtonLocator = By.xpath("//*[@id='auth-container__forms']//button[@class='auth-box__auth-submit auth__btn auth__btn--green']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private LoginPage typeUserName(String username){
        WebElement loginField = (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<WebElement>(){
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.id("myDynamicElement"));
                    }});

        loginField.sendKeys(username);
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
