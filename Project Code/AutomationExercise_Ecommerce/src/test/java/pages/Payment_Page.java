package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.PaymentData;

public class Payment_Page {

    WebDriver driver;

    public Payment_Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name_on_card")
    private WebElement nameOnCard;

    @FindBy(name = "card_number")
    private WebElement cardNumber;

    @FindBy(name = "cvc")
    private WebElement cvc;

    @FindBy(name = "expiry_month")
    private WebElement expMonth;

    @FindBy(name = "expiry_year")
    private WebElement expYear;

    @FindBy(id = "submit")
    private WebElement payAndConfirmBtn;

    public void paymentCompletion(PaymentData data) {

        nameOnCard.clear();
        nameOnCard.sendKeys(data.getNameOnCard());

        cardNumber.clear();
        cardNumber.sendKeys(data.getCardNumber());

        cvc.clear();
        cvc.sendKeys(data.getCvc());

        expMonth.clear();
        expMonth.sendKeys(data.getExpMonth());

        expYear.clear();
        expYear.sendKeys(data.getExpYear());

        payAndConfirmBtn.click();
    }
}
