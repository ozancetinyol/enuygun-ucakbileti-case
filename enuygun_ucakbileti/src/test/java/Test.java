import Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Test {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @org.testng.annotations.Test
    public void case1() throws InterruptedException {
        homePage.goToEnuygunHomepage();
        homePage.fromWhereToWhere("İstanbul", "Ankara");
        homePage.datePicker("20", "21");
        homePage.searchButton();
        homePage.chooseDepartureTimeFilter();
        homePage.checkFilter();
    }

    @org.testng.annotations.Test
    public void case2() throws InterruptedException {
        homePage.goToEnuygunHomepage();
        homePage.fromWhereToWhere("İstanbul", "Ankara");
        homePage.datePicker("24", "25");
        homePage.searchButton();
        homePage.chooseDepartureTimeFilter();
        homePage.checkFilter();
        homePage.chooseAirlineFilter();
        homePage.checkPriceThy();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}

