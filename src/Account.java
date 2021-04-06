import java.util.UUID;
import java.util.ArrayList;

/**
 * this class stores the data of each account
 * like serial, id, name and...
 * @author Alireza Nejadipour
 * @version 2.2 (3/25/2021)
 */


public class Account
{
    private final UUID serial;
    private final String id;
    private String firstName;
    private String lastName;
    private final String type;
    private int balance;
    private final ArrayList<Transaction> transactions;

    /**
     * creat a new account with given parameters
     * @param id id of new account
     * @param firstName first name of the owner of new account
     * @param lastName last name of the owner of new account
     * @param type type of new account
     * @param balance balance of the account
     */
    public Account(String id, String firstName, String lastName, String type, int balance)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        this.serial = UUID.randomUUID();

    }


    /**
     * updates the balance after each transaction
     * @param amount is the change to the balance
     */
    public void updateBalance(int amount)
    {
        this.balance += amount;

    }


    /**
     * adds a new transaction to the transactions' list
     * @param transaction new transaction that should be added
     */
    public void addTransaction(Transaction transaction)
    {
        transactions.add(transaction);

    }


    /**
     * print all the transactions of the account
     */
    public void printTransactions()
    {
        if(transactions.size() == 0)
        {
            System.out.println("No transaction yet.");

        }
        else
        {
            for (Transaction transaction : transactions)
            {
                transaction.print();

            }

        }

    }


    /**
     * print information of the account
     */
    public void printAccountData()
    {
        System.out.println(getSerial().toString() + ", " + getType() + ", " + getBalance());

    }

    /***
     * checks if 2 accounts are equal to each other or not
     * @param accountToCompare the account to compare with
     * @return true if they are equal
     */
    public boolean equals(Account accountToCompare)
    {
        // compare user to itself
        if (accountToCompare == this)
        {
            return true;

        }

        // check if user is instance of User or not!
        if (!(accountToCompare instanceof Account))
        {
            return false;

        }

        return serial.equals(accountToCompare.serial);

    }


    /**
     * get the serial of the account
     * @return serial field
     */
    public UUID getSerial()
    {
        return serial;

    }

    /**
     * get the id of the owner of the account
     * @return id field
     */
    public String getId()
    {
        return id;

    }

    /**
     * get the type of the account
     * @return type field
     */
    public String getType()
    {
        return type;

    }

    /**
     * get the balance of the account
     * @return balance field
     */
    public int getBalance()
    {
        return balance;

    }

}