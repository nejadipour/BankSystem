import java.util.*;
import java.text.*;

/**
 * this class stores the information of each transaction
 * like date and amount of it
 * @author Alireza Nejadipour
 * @version 2.2 (3/25/2021)
 */


public class Transaction
{
    private final int amount;
    private final Date date;
    private final SimpleDateFormat dateFormat;

    /**
     * create a new transaction with given amount
     * @param amount amount of transaction
     */
    public Transaction(int amount)
    {
        this.amount = amount;
        date = new Date();
        dateFormat = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a");

    }

    /**
     * the method that prints the information of each transaction
     */
    public void print()
    {
        if (getAmount() > 0)
            System.out.println("amount : " + "+" + getAmount());
        else
            System.out.println("amount : " + getAmount());

        System.out.println("date   : " + dateFormat.format(getDate()));

    }


    /**
     * get the amount of transaction
     * @return amount field
     */
    public int getAmount()
    {
        return amount;

    }

    /**
     * get the date of transaction
     * @return date field
     */
    public Date getDate()
    {
        return date;

    }

}