import org.openqa.selenium.WebDriver;

public class TestUtil {
    private final static String fileConfigPath = "src\\main\\resources\\config.properties";
    private static JSONReader jsonReader;
    static WebDriver driver;
    static String webSite;
    static String loginOnliner;
    static String passwordOnliner;
    static int pageTimeout;

    static {
        jsonReader = new JSONReader(fileConfigPath);
        driver = DriverSingleton.getDriver(jsonReader.getBrowserName());
        webSite = jsonReader.getWebSite();
        loginOnliner = jsonReader.getLoginOnliner();
        passwordOnliner = jsonReader.getPasswordOnliner();
        pageTimeout = jsonReader.getPagetTimeout();
    }
}
