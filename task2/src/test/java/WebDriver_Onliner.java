import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by a.maley on 12.10.2016.
 */
public class WebDriver_Onliner {

    private static JSONReader jsonReader;
    private final static String fileConfigPath = "src\\main\\resources\\config.properties";
    private static WebDriver driver;
    private static String webSite;
    private static String loginOnliner;
    private static String passwordOnliner;

    @BeforeClass
    public static void setUp(){
        jsonReader = new JSONReader(fileConfigPath);
        driver = DriverSingleton.getDriver(jsonReader.getBrowserName());
        webSite = jsonReader.getWebSite();
        loginOnliner = jsonReader.getLoginOnliner();
        passwordOnliner = jsonReader.getPasswordOnliner();

    }
    @Test
    public void test() throws InterruptedException {
        driver.get(webSite);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement entryBtnElement = driver.findElement(By.xpath("//*[@id='userbar']//*[contains(text(),'Вход ')]"));
        entryBtnElement.click();

        WebElement authorizingElement = driver.findElement(By.xpath("//*[@id='auth-container__forms']//input[@placeholder='Ник или e-mail']"));
        authorizingElement.sendKeys(loginOnliner);
        authorizingElement = driver.findElement(By.xpath("//*[@id='auth-container__forms']//input[@placeholder='Пароль']"));
        authorizingElement.sendKeys(passwordOnliner);
        authorizingElement.submit();

        List<WebElement> popularCategories = driver.findElements(By.xpath("//div[@id='container']//ul[@class='project-navigation__list project-navigation__list_secondary']"));
        System.out.println(popularCategories);
        for (WebElement element : popularCategories){
            System.out.println(element.getText());
        }






        WebElement unauthorizedElement = driver.findElement(By.xpath("//*[@id='userbar']//a[@class='exit']"));
        unauthorizedElement.click();
        driver.quit();

    }


}
