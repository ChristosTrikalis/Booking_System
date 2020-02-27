package application;
import data.DataHandler;

public class Payment implements PaymentInterface
{
    private final double mediumPrice = 130.0;
    private final double premiumPrice = 250.0;
    private int countOfPeople;
    private boolean premium;
    public Payment(int countOfPeople,boolean premium)
    {
        this.countOfPeople = countOfPeople;
        this.premium = premium;
    }
    private double calculatePrice()
    {
        int capacity = countOfPeople/5;
        if(premium)
        {
           return capacity*premiumPrice;
        }
        else {
            return capacity*mediumPrice;
        }
    }
    private void insert(String fname,String lname,String email,String phone)
    {
        DataHandler dataHandler = new DataHandler("Admin", 231, "AxwQ4251/JamesonSucks96");
        dataHandler.insert(dataHandler.getId() + 1, fname, lname, email, phone, countOfPeople);
        dataHandler.closeConn();
    }



    public void insertValues(String fname,String lname,String email,String phone)
    {

        insert(fname,lname,email,phone);
    }
    public double price()
    {

        return calculatePrice();
    }
}
