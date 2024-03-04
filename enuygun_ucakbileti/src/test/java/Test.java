import Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Test {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @org.testng.annotations.Test
    public void case1() throws InterruptedException {
        homePage.goToEnuygunHomepage();
        homePage.fromWhereToWhere();
        homePage.datePicker();
        homePage.searchButton();
        homePage.chooseFilter();
        homePage.checkFilter();
    }

    @org.testng.annotations.Test
    public void case2() throws InterruptedException {
        homePage.goToEnuygunHomepage();
        homePage.fromWhereToWhere();
        homePage.datePicker();
        homePage.searchButton();

    }

    @AfterTest
    public void tearDown() {
        // WebDriver'ı kapat
        driver.quit();
    }

}

