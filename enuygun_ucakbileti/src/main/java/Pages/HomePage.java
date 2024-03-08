package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@data-testid='search-round-trip-text']")
    WebElement roundTrip;

    @FindBy(css = "[data-testid='flight-origin-input-comp']")
    WebElement fromWhere;

    @FindBy(css = "[data-testid='endesign-flight-origin-autosuggestion-option-item-0']")
    WebElement fromWhereClick;

    @FindBy(css = "[data-testid='flight-destination-input-comp']")
    WebElement toWhere;

    @FindBy(css = "[data-testid='endesign-flight-destination-autosuggestion-option-item-0']")
    WebElement toWhereClick;

    @FindBy(css = "[data-testid='enuygun-homepage-flight-departureDate-input-comp']")
    WebElement departureDate;

    @FindBy(css = "[data-testid='enuygun-homepage-flight-returnDate-input-comp']")
    WebElement returnDate;

    @FindBy(css = "[data-testid='enuygun-homepage-flight-submitButton']")
    WebElement searchButton;

    @FindBy(css = "[data-testid='favorite-search-add-button']")
    WebElement addFavoriteButton;

    @FindBy(css = "[class='ctx-filter-departure-return-time ei-expand-more ']")
    WebElement departureTimeFilter;

    @FindBy(xpath = "(//*[@class='rc-slider'])[1]")
    WebElement slider;

    @FindBy(xpath = "(//*[@role='slider'])[1]")
    WebElement startPointOfSlider;

    @FindBy(xpath = "(//*[@role='slider'])[2]")
    WebElement endPointOfSlider;

    @FindBy(xpath = "//div[@class='flight-departure-time']")
    List<WebElement> departureTimes;

    @FindBy(css = "[class='ctx-filter-airline card-header']")
    WebElement openAirlineFilter;

    @FindBy(xpath = "(//*[@class='filter-label'])[5]")
    WebElement chooseAirlineFilter;

    @FindBy(css = "[class='money-int']")
    List<WebElement> priceElements;


    public void goToEnuygunHomepage() {

        driver.get("https://www.enuygun.com/");
    }

    public void fromWhereToWhere(String nereden, String nereye) throws InterruptedException {

        roundTrip.click();
        fromWhere.click();
        fromWhere.sendKeys(nereden);
        Thread.sleep(1000);
        fromWhereClick.click();
        toWhere.click();
        toWhere.sendKeys(nereye);
        Thread.sleep(1000);
        toWhereClick.click();
    }

    public void datePicker(String gidisTarihi, String dönüsTarihi) throws InterruptedException {

        departureDate.click();
        WebElement departureDatePicker = driver.findElement(By.xpath("(//*[@class='sc-gdfaqJ fjsa-dD'])[1] //*[@class='sc-iZzKWI dlKEMh'] //*[@class='sc-cQCQeq hWjVug'] //*[@data-day='" + gidisTarihi + "']"));
        departureDatePicker.click();
        returnDate.click();
        WebElement returnDatePicker = driver.findElement(By.xpath("(//*[@class='sc-gdfaqJ fjsa-dD'])[1] //*[@class='sc-iZzKWI dlKEMh'] //*[@class='sc-cQCQeq hWjVug'] //*[@data-day='" + dönüsTarihi + "']"));
        returnDatePicker.click();
    }

    public void searchButton() throws InterruptedException {

        searchButton.click();
        Thread.sleep(5000);
        Assert.assertTrue(addFavoriteButton.isDisplayed(), "Sayfa yüklenirken bir hata oluştu!!!");
        System.out.println("Sayfa başarıyla yüklendi");
    }

    public void chooseDepartureTimeFilter() throws InterruptedException {

        Actions sliderAction = new Actions(driver);
        departureTimeFilter.click();
        int xOffsetStart = 100;
        int xOffsetEnd = -60;
        sliderAction.dragAndDropBy(startPointOfSlider, xOffsetStart, 0).build().perform();
        Thread.sleep(2000);
        sliderAction.dragAndDropBy(endPointOfSlider, xOffsetEnd, 0).build().perform();
        Thread.sleep(2000);
    }

    public void checkFilter() {

        boolean isWithinRange = true;
        for (WebElement departureTime : departureTimes) {
            String timeString = departureTime.getText();
            String[] timeParts = timeString.split(":");
            int hour = Integer.parseInt(timeParts[0]);
            if (hour < 10 || hour > 18) {
                isWithinRange = false;
                break;
            }
            System.out.println("Kalkış saatleri: " + hour);
        }

        if (isWithinRange) {
            System.out.println("Tüm uçuşların kalkış saatleri 10:00 ile 18:00 arasında.");
        } else {
            System.out.println("Bazı uçuşların kalkış saatleri 10:00 ile 18:00 arasında değil.");
        }
    }

    public void chooseAirlineFilter() throws InterruptedException {

        openAirlineFilter.click();
        Thread.sleep(2000);
        chooseAirlineFilter.click();
        Thread.sleep(2000);
    }

    public void checkPriceThy() {

        List <Double> prices = new ArrayList<>();

        for (WebElement element : priceElements){
            String priceText = element.getText().replace(" TL", "").replace(",",".");
            double price = Double.parseDouble(priceText);
            prices.add(price);
        }

        System.out.println(prices);

        List <Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);

        boolean isSorted = prices.equals(sortedPrices);

        if (isSorted){
            System.out.println("Fiyatlar küçükten büyüğe sıralanıyor.");
        }else{
            System.out.println("Fiyat sıralaması hatalı");
        }
    }
}
