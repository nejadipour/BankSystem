import java.util.ArrayList;
import java.util.Iterator;

/**
 * this class stores general data
 * like the data of all users
 * @author Alireza Nejadipour
 * @version 3.2 (3/25/2021)
 */


public class BankingSystem
{
    private final ArrayList<User> users;
    private final ArrayList<Account> accounts;

    /**
     * create a new bank system
     */
    public BankingSystem()
    {
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();

    }


    /**
     * gets a new user and adds it to the list of users
     * @param user new user that should be added
     */
    public void register(User user)
    {
        if (userExist(user))
        {
            System.out.println("user already exists.");
            return;
        }

        addUser(user);
        System.out.println("user created.");

    }


    /**
     * gets id and password and finds them in data
     * @param id the id of user to be found
     * @param password the password to be checked
     */
    public boolean login(String id, String password)
    {
        for (User user : users)
        {
            if (user.getId().equals(id))
            {
                if (user.getPassword().equals(password))
                {
                    System.out.println("Logged in.");
                    return true;
                }
                else
                {
                    System.out.println("user doesn't exists or password is incorrect");
                    return false;
                }

            }

        }
        System.out.println("user doesn't exists or password is incorrect");
        return false;

    }


    /**
     * adds user to the list of users
     * @param user the user to be added
     */
    public void addUser(User user)
    {
        users.add(user);

    }


    /**
     * removes user from the list of users
     * @param user the user to be removed
     */
    public void removeUser(User user)
    {
        for (Account account : user.getAccounts())
        {
            removeAccount(account);

        }

        this.users.remove(user);

    }


    /**
     * prints all the users available in the system
     */
    public void displayUsers()
    {
        int userNum = 1;

        if (users.size() == 0)
        {
            System.out.println("No users found.");

        }
        else
        {
            for (User user : users)
            {
                System.out.print("User " + userNum + ": ");
                user.printUserData();

                userNum++;

            }

        }

    }


    /**
     * adds account to the list of accounts
     * @param account the account to be added
     */
    public void addAccount(Account account)
    {
        accounts.add(account);

    }


    /**
     * removes account from the list of accounts
     * @param account the account to be removed
     */
    public void removeAccount(Account account)
    {
        accounts.remove(account);

    }


    /**
     * prints all the accounts available in the system
     */
    public void displayAccounts()
    {
        int accountNum = 1;

        if (accounts.size() == 0)
        {
            System.out.println("No accounts found.");

        }
        else
        {
            for (Account account : accounts)
            {
                System.out.print("Account " + accountNum + ": ");
                account.printAccountData();

                accountNum++;

            }

        }

    }


    /**
     * gets a string and checks the accounts' list to find the serial
     * @param serial the serial of the account to be found
     * @return if it is found returns the account
     */
    public Account findAccount(String serial)
    {
        for (Account account : accounts)
        {
            if (account.getSerial().toString().equals(serial))
            {
                return account;

            }

        }

        return null;

    }


    /**
     * gets a string and checks the users' list to find the id
     * @param id the id of the user to be found
     * @return if it is found returns the user
     */
    public User findUser(String id)
    {
        Iterator<User> userIterator = users.iterator();

        while (userIterator.hasNext())
        {
            User user = userIterator.next();
            if (user.getId().equals(id))
            {
                return user;

            }

        }

        return null;

    }


    /**
     * the input is a user and the method checks if the user is available or mot
     * @param user the user to be checked
     * @return if the user is available returns true
     */
    public boolean userExist(User user)
    {
        Iterator<User> userIterator = users.iterator();

        while (userIterator.hasNext())
        {
            User userToCheck = userIterator.next();

            if (userToCheck.equals(user))
            {
                return true;

            }

        }

        return false;

    }

}