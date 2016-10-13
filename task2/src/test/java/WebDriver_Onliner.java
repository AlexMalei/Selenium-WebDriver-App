import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by a.maley on 12.10.2016.
 */
public class WebDriver_Onliner {

    private static  WebDriver driver;


    @BeforeClass
    public static void setUp(){
        driver = TestUtil.driver;
        driver.get(TestUtil.webSite);
    }

    @Test(priority = 1)
    public void onlinerSiteTest_mainPageOpened(){
        assertEquals(driver.getTitle(), "Onliner.by");
    }


    @Test(priority = 2)
    public void onlinerSiteTest_isAuthorized(){
        MainPage mainPage = new MainPage(driver);
        assertTrue(mainPage.clickLoginButton().loginAs(TestUtil.loginOnliner, TestUtil.passwordOnliner).isAuthorized());
    }

    @Test(priority = 3)
    public void onlinerSiteTest_isCorrectChosenTopic(){
        MainPage mainPage = new MainPage(driver);
        String expextedTopic = mainPage.getRandomTopic();
        String actualTopic = mainPage.goRandomTopic(expextedTopic).getTopicOnPageAndReturnMain();

        assertEquals(actualTopic, expextedTopic);
    }


    @Test(priority = 4)
    public void onlinerSiteTest_testLogout(){
        MainPage mainPage = new MainPage(driver);
        mainPage.manageNews();
        assertFalse(mainPage.logOut().isAuthorized());
    }





    @AfterClass
    public void tearDown(){
        driver.quit();
    }



}
