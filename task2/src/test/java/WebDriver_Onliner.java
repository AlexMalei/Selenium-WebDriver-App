import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sun.applet.Main;


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
    public void test()  {
        driver.get(webSite);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton().loginAs(loginOnliner, passwordOnliner);

        List<WebElement> popularCategories = driver.findElements(By.xpath("//div[@id='container']//ul[@class='project-navigation__list project-navigation__list_secondary']"));

        for (WebElement element : popularCategories){
            System.out.println(element.getText());
        }






        WebElement unauthorizedElement = driver.findElement(By.xpath("//*[@id='userbar']//a[@class='exit']"));
        unauthorizedElement.click();
        driver.quit();

    }


}
