import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class WebDriver_Onliner {

    private static  WebDriver driver;


    @BeforeClass
    public static void setUp(){
        driver = TestUtil.driver;
        driver.get(TestUtil.webSite);
    }

    @Test
    public void onlinerSiteTest_makeLogInOutTestWithSerchingNews(){
        MainPage mainPage = new MainPage(driver);

        assertEquals(mainPage.getTitle(), "Onliner.by");
        assertTrue(mainPage.clickLoginButton().loginAs(TestUtil.loginOnliner, TestUtil.passwordOnliner).isAuthorized());

        String expextedTopic = mainPage.getRandomTopic();
        TopicPage topicPage = mainPage.goRandomTopic(expextedTopic);
        String actualTopic = topicPage.getTopicOnPage();

        assertEquals(expextedTopic, actualTopic);

        mainPage = topicPage.returnMainPage();
        mainPage.manageNews();

        assertFalse(mainPage.logOut().isAuthorized());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }



}
