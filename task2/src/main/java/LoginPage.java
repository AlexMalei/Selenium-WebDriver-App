import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


/**
 * Created by a.maley on 12.10.2016.
 */
public class LoginPage {
    private final WebDriver driver;
    private String loginPlaceholder = "'Ник или e-mail'";
    private String passwordPlaceholder = "'Пароль'";
    private String authorizingField = "//*[@id='auth-container__forms']//input[@placeholder=%s]";

    private By loginFieldLocator = By.xpath(String.format(authorizingField, loginPlaceholder));
    private By passwordFieldLocator = By.xpath(String.format(authorizingField, passwordPlaceholder));
    private By loginButtonLocator = By.xpath("//*[@id='auth-container__forms']//button[@class='auth-box__auth-submit auth__btn auth__btn--green']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private LoginPage typeUserName(String username){
        new WebDriverWait(driver, TestUtil.pageTimeout).until(new ExpectedCondition<WebElement>(){
                    public WebElement apply(WebDriver d) {
                        return d.findElement(loginFieldLocator);
                    }}).sendKeys(username);
        return this;
    }


    private LoginPage typePassword(String password){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(TestUtil.pageTimeout, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(passwordFieldLocator);
            }
        }).sendKeys(password);
        return this;
    }

    private MainPage submitLogin(){
        driver.manage().timeouts().implicitlyWait(TestUtil.pageTimeout, TimeUnit.SECONDS);
        driver.findElement(loginButtonLocator).submit();
        return new MainPage(driver);
    }

    public MainPage loginAs(String login, String password){
        typeUserName(login);
        typePassword(password);
        return submitLogin();

    }
}
