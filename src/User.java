import java.util.ArrayList;

/**
 * this class stores the data of each user of the bank
 * like name, id, password and...
 * @author Alireza Nejadipour
 * @version 3.2 (3/25/2021)
 */


public class User
{
    private final String firstName;
    private final String lastName;
    private final String id;
    private final String password;
    private final ArrayList<Account> accounts;

    /**
     * create a new user with given parameters
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param id id of the user
     * @param password password of the user
     */
    public User(String firstName, String lastName, String id, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.password = password;
        this.accounts = new ArrayList<>();

    }


    /**
     * add an account to the list of the accounts
     * @param account the account that should be added to the accounts
     */
    public void addAccount(Account account)
    {
        accounts.add(account);

    }


    /**
     *  remove an account from the list od accounts
     * @param account the account that should be removed from the accounts
     */
    public void removeAccount(Account account)
    {
        accounts.remove(account);

    }


    /**
     * checks if two objects are equal by their fields
     * @param userToCompare the user to be compared
     * @return if they are equal returns true
     */
    public boolean equals(User userToCompare)
    {
        // compare user to itself
        if (userToCompare == this)
        {
            return true;
        }

        // check if user is instance of User or not!
        if (!(userToCompare instanceof User))
        {
            return false;
        }

        return id.equals(userToCompare.id);

    }


    /**
     * gets an account and the amount that should be deposited
     * @param account the account that should deposit from
     * @param amount the amount of deposit
     */
    public void deposit(Account account, int amount)
    {
        for (Account accountToDeposit : accounts)
        {
            if (accountToDeposit.equals(account))
            {
                accountToDeposit.updateBalance(amount);

                Transaction newTransaction = new Transaction(amount);
                account.addTransaction(newTransaction);

                System.out.println("\nCompleted.");
                return;

            }

        }

    }


    /**
     * gets an account and the amount that should withdraw
     * @param account the account that should withdraw to
     * @param amount the amount to withdraw
     */
    public void withdrawal(Account account, int amount)
    {
        for (Account accountToWithdraw : accounts)
        {
            if (accountToWithdraw.equals(account))
            {
                int currentBalance = accountToWithdraw.getBalance();

                if (currentBalance < amount)
                {
                    System.out.println("\nNot enough money.");

                }
                else
                {
                    accountToWithdraw.updateBalance(-1 * amount);

                    Transaction newTransaction = new Transaction(-1 * amount);
                    account.addTransaction(newTransaction);

                    System.out.println("\nCompleted.");

                }

                return;

            }

        }

    }

    /**
     * gets two accounts and transfers money from source to destination
     * @param srcAccount the account to get money from
     * @param destAccount the account to send money to
     * @param amount the amount of money to transfer
     */
    public void transfer(Account srcAccount, Account destAccount, int amount)
    {
        srcAccount.updateBalance(-1 * amount);
        destAccount.updateBalance(amount);

        Transaction srcTransaction = new Transaction(-1 * amount);
        Transaction destTransaction = new Transaction(amount);

        srcAccount.addTransaction(srcTransaction);
        destAccount.addTransaction(destTransaction);

    }


    /**
     * gets account and prints the balance of it
     * @param account the account to print its balance
     */
    public void checkBalance(Account account)
    {
        System.out.println("\nAccount's Balance : " + account.getBalance());

    }

    /**
     * prints all the accounts that are available
     */
    public boolean printAllAvailableAccounts()
    {
        int accountNum = 1;

        if (accounts.size() == 0)
        {
            System.out.println("You don't have any account right now.");
            return false;

        }
        else
        {
            for (Account account : accounts)
            {
                System.out.print("Account " + accountNum + ":");
                account.printAccountData();

                accountNum++;

            }
            return true;

        }

    }


    /**
     * prints the information about the user
     */
    public void printUserData()
    {
        System.out.println(getFirstName() + " " + getLastName() + " " + getId());

    }

    /**
     * get the first name of the user
     * @return firstName field
     */
    public String getFirstName()
    {
        return firstName;

    }

    /**
     * get the last name of the user
     * @return lastName field
     */
    public String getLastName()
    {
        return lastName;

    }

    /**
     * get the id of the user
     * @return id field
     */
    public String getId()
    {
        return id;

    }

    /**
     * get the password of the user
     * @return password field
     */
    public String getPassword()
    {
        return password;

    }

    /**
     * get the list of accounts of the user
     * @return accounts field
     */
    public ArrayList<Account> getAccounts()
    {
        return accounts;

    }

}