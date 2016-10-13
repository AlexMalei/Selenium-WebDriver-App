import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverSingleton {

    private static  WebDriver driver;

    public static synchronized WebDriver getDriver(String driverSrting) {
        if (driver == null) {
            driverSrting = driverSrting.toLowerCase();

            if (driverSrting.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
                driver = new FirefoxDriver();
            }
            if (driverSrting.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
            }
            if (driverSrting.equals("explorer")) {
                System.setProperty("webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            }
        }
        return driver;
    }
}
