package utils;



public class PaymentData {

    private String nameOnCard;
    private String cardNumber;
    private String cvc;
    private String expMonth;
    private String expYear;

    public PaymentData(String nameOnCard, String cardNumber, String cvc, String expMonth, String expYear) {
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    public String getNameOnCard() { return nameOnCard; }
    public String getCardNumber() { return cardNumber; }
    public String getCvc() { return cvc; }
    public String getExpMonth() { return expMonth; }
    public String getExpYear() { return expYear; }
}
