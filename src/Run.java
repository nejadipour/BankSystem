import java.util.Scanner;

/**
 * this class is made to run all the process of the system
 * @author Alireza Nejadipour
 * @version 4.4 (3/25/2021)
 */


public class Run
{
    private final String BOLDStart;
    private final String BOLDEnd;
    private int choice;
    private String amountString;
    private int amount;
    private final String mainMenu;
    private final String logInMenu;
    private final String transactionMenu;
    private final String sysAdminMenu;
    private final String afterTransactionMenu;
    private final Scanner scanner;
    private final BankingSystem runTimeBankSystem;
    private User runTimeUser;
    private Account runTimeAccount;
    private final String adminUsername;
    private final String adminPassword;

    /**
     * create a new run
     */
    public Run()
    {
        BOLDStart = "\033[0;1m";
        BOLDEnd = "\033[0;0m";

        // this message prints when a new run is made
        System.out.println("\n" + BOLDStart + "Note: if u wanna go back to the main menu type it in the input place." + BOLDEnd + "\n");

        // menus needed during the runtime
        mainMenu =
                """
                        1.Sign up
                        2.Log in
                        3.System Admin
                        4.Exit
                        """;

        logInMenu =
                """
                        1.Existing accounts
                        2.Add new account
                        3.Log out
                        4.Remove account
                        5.Main Menu
                        """;

        transactionMenu =
                """
                        1.Withdrawal
                        2.Deposit
                        3.Transfer
                        4.Check Balance
                        5.Back
                        6.Transactions
                        """;

        sysAdminMenu =
                """
                        1.display users
                        2.display accounts
                        3.remove user
                        4.remove account
                        5.Main Menu
                        """;

        afterTransactionMenu =
                """
                        1.Login Menu
                        2.Another Transaction
                        3.Main Menu
                        """;

        scanner = new Scanner(System.in).useDelimiter("\n");
        runTimeBankSystem = new BankingSystem();

        adminUsername = "sysadmin";
        adminPassword = "1234";

    }


    /**
     * scans the user command and checks if it is main menu or not
     * if it is main menu prints the main menu
     * and if it is not stores the input in choice field
     */
    public void userChoice()
    {
        System.out.print(BOLDStart + "Your choice                  : " + BOLDEnd);
        String choiceString = scanner.next();

        if(choiceString.equals("main menu"))
        {
            printMainMenu();

        }
        else
        {
            choice = Integer.parseInt(choiceString);

        }

    }


    /**
     * some inputs are not supported by the userChoice method
     * this method checks the input if it is main menu or not
     * @param input the string to be checked
     */
    public void checkMainMenu(String input)
    {
        if (input.equals("main menu"))
        {
            printMainMenu();

        }

    }


    /**
     * prints the main menu and then the user decides what to happen
     */
    public void printMainMenu()
    {
        System.out.println(mainMenu);

        userChoice();

        switch (choice)
        {
            case 1 -> signUp();
            case 2 -> logIn();
            case 3 -> systemAdmin();
            case 4 ->
            {
                System.out.println("Hope you great time!");
                System.exit(0);
            }
            default ->
            {
                System.out.println("Invalid Input.");
                printMainMenu();

            }

        }

    }


    /**
     * this method scans details needed to make a new user and then calls the method register
     */
    public void signUp()
    {
        String firstName;
        String lastName;
        String id;
        String password;

        System.out.println(BOLDStart + "Enter Info" + BOLDEnd);

        System.out.print("First Name                   : ");
        firstName = scanner.next();
        checkMainMenu(firstName);

        System.out.print("Last Name                    : ");
        lastName = scanner.next();
        checkMainMenu(lastName);

        System.out.print("ID                           : ");
        id = scanner.next();
        checkMainMenu(id);

        if(id.length() != 10)
        {
            System.out.println("Invalid id(length)");
            printMainMenu();
            return;
        }

        System.out.print("Password                     : ");
        password = scanner.next();
        checkMainMenu(password);

        runTimeUser = new User(firstName, lastName, id, password);

        runTimeBankSystem.register(runTimeUser);

        printMainMenu();

    }


    /**
     * when the user wants to access its options he should login by this method
     * the method scans id and password to check if user is found or not
     */
    public void logIn()
    {
        String password;
        String id;
        System.out.println(BOLDStart + "Enter user ID and password" + BOLDEnd);

        System.out.print("ID                           : ");
        id = scanner.next();
        checkMainMenu(id);

        System.out.print("Password                     : ");
        password = scanner.next();
        checkMainMenu(password);

        if (runTimeBankSystem.login(id, password))
        {
            printLogInMenu();

        }
        else
        {
            printMainMenu();

        }

    }

    /**
     * prints the menu after login
     * the user decides the next thing to happen
     */
    public void printLogInMenu()
    {
        System.out.println(logInMenu);

        userChoice();

        switch (choice)
        {
            case 1 -> existingAccounts();
            case 2 ->
            {
                addNewAccount();
                printLogInMenu();
            }
            case 3 ->
            {
                runTimeUser = null;
                System.out.println("Logged out successfully.");
                printMainMenu();
            }
            case 4 ->
            {
                removeAccount();
                printLogInMenu();
            }
            case 5 -> printMainMenu();
            default ->
            {
                System.out.println("Invalid Input.");
                printLogInMenu();

            }
        }

    }


    /**
     * prints all the accounts of the user
     * user should decide an account to go to the transaction part
     */
    public void existingAccounts()
    {
        if (runTimeUser.printAllAvailableAccounts())
        {
            userChoice();

            runTimeAccount = runTimeUser.getAccounts().get(choice - 1);

            if (runTimeUser.getAccounts().get(choice - 1) == null)
            {
                System.out.println("Invalid input.");
                printLogInMenu();

            }

            System.out.println("Logged into account.");

            printTransactionMenu();

        }
        else
        {
            printLogInMenu();

        }

    }


    /**
     * prints all available transactions
     * user decides what he wants to do
     */
    public void printTransactionMenu()
    {
        System.out.println(transactionMenu);

        userChoice();

        switch (choice)
        {
            case 1 ->
            {
                withdrawal();
                afterTransaction();
            }
            case 2 ->
            {
                deposit();
                afterTransaction();
            }
            case 3 ->
            {
                transfer();
                afterTransaction();
            }
            case 4 ->
            {
                runTimeUser.checkBalance(runTimeAccount);
                afterTransaction();
            }
            case 5 ->
            {
                System.out.println("Logged out of account.");
                printLogInMenu();
            }
            case 6 ->
            {
                runTimeAccount.printTransactions();
                afterTransaction();
            }
            default ->
            {
                System.out.println("Invalid input.");
                printTransactionMenu();

            }

        }

    }


    /**
     * after each transaction this method is called
     * the user will decide what to happen
     */
    public void afterTransaction()
    {
        System.out.println(afterTransactionMenu);

        userChoice();

        switch (choice)
        {
            case 1 -> printLogInMenu();
            case 2 -> printTransactionMenu();
            case 3 -> printMainMenu();
            default ->
            {
                System.out.println("Invalid Input.");
                afterTransaction();

            }

        }

    }


    /**
     * scans the details needed for withdrawal
     * and calls the method
     */
    public void withdrawal()
    {
        System.out.print("Amount                       : ");
        amountString = scanner.next();
        checkMainMenu(amountString);

        amount = Integer.parseInt(amountString);

        if (invalidAmount(amount))
        {
            System.out.println("Invalid amount.");
            printTransactionMenu();

        }
        else
        {
            runTimeUser.withdrawal(runTimeAccount, amount);

        }


    }


    /**
     * scans the details needed for deposit
     * and calls the method
     */
    public void deposit()
    {
        System.out.print("Amount                       : ");
        amountString = scanner.next();
        checkMainMenu(amountString);

        amount = Integer.parseInt(amountString);

        if (invalidAmount(amount))
        {
            System.out.println("Invalid amount.");
            printTransactionMenu();

        }
        else
        {
            runTimeUser.deposit(runTimeAccount, amount);

        }


    }


    /**
     * scans the details needed for transfer transaction
     * calls the method and if it is done successfully prints message related
     */
    public void transfer()
    {
        System.out.print("Destination Account's Serial : ");
        String destAccountSerial = scanner.next();
        checkMainMenu(destAccountSerial);

        System.out.print("Amount                       : ");
        amountString = scanner.next();
        checkMainMenu(amountString);

        amount = Integer.parseInt(amountString);

        if (invalidAmount(amount))
        {
            System.out.println("Invalid amount.");

        }
        else
        {
            Account destAccount = runTimeBankSystem.findAccount(destAccountSerial);

            if (destAccount == null || runTimeAccount.getBalance() < amount)
            {
                System.out.println("Destination account doesn't exist or there is not enough money in your account.");

            }
            else
            {
                runTimeUser.transfer(runTimeAccount, destAccount, amount);
                System.out.println("Completed");

            }

        }

    }


    /**
     * scans details needed to make a new account
     * and calls the related method to make a new account for the user
     */
    public void addNewAccount()
    {
        System.out.print("Account type                 : ");
        String type = scanner.next();
        checkMainMenu(type);

        System.out.print("Amount                       : ");
        String amount = scanner.next();
        checkMainMenu(amount);
        int intAmount = Integer.parseInt(amount);

        Account newAccount = new Account(runTimeUser.getId(), runTimeUser.getFirstName(),
                             runTimeUser.getLastName(), type, intAmount);

        runTimeUser.addAccount(newAccount);
        runTimeBankSystem.addAccount(newAccount);

        System.out.println("New account opened.");

        runTimeAccount = newAccount;

    }


    /**
     * special admin of the bank system will login from this part
     * the username and password is scanned and if it is correct
     * the system admin will access all the data
     */
    public void systemAdmin()
    {
        System.out.print("Admin username               : ");
        String username = scanner.next();
        checkMainMenu(username);

        System.out.print("Admin password               : ");
        String password = scanner.next();
        checkMainMenu(password);

        if (!username.equals(adminUsername) || !password.equals(adminPassword))
        {
            System.out.println("Username or password is incorrect.");
            printMainMenu();
        }
        else
        {
            System.out.println("Logged in as sysadmin.");
            printSysAdminMenu();
        }

    }


    /**
     * after successful login by the system admin the menu related to him will print
     * the admin decides what to do
     */
    public void printSysAdminMenu()
    {
        System.out.println(sysAdminMenu);

        userChoice();

        switch (choice)
        {
            case 1 ->
            {
                runTimeBankSystem.displayUsers();
                printSysAdminMenu();
            }
            case 2 ->
            {
                runTimeBankSystem.displayAccounts();
                printSysAdminMenu();
            }
            case 3 ->
            {
                removeUser();
                printSysAdminMenu();
            }
            case 4 ->
            {
                removeAccount();
                printSysAdminMenu();
            }
            case 5 -> printMainMenu();
            default ->
            {
                System.out.println("Invalid Input.");
                printSysAdminMenu();

            }

        }

    }


    /**
     * the admin will enter information needed to remove user
     */
    public void removeUser()
    {
        System.out.print("Enter the ID                 : ");
        String id = scanner.next();
        checkMainMenu(id);

        User userToRemove = new User("first", "last", id, "password");

        if (runTimeBankSystem.userExist(userToRemove))
        {
            userToRemove = runTimeBankSystem.findUser(id);
            runTimeBankSystem.removeUser(userToRemove);
            System.out.println("User removed.");

        }
        else
        {
            System.out.println("User doesn't exist.");

        }

    }


    /**
     * the admin enters the information of account to be removed
     */
    public void removeAccount()
    {
        System.out.print("Enter the account's serial   : ");
        String serial = scanner.next();
        checkMainMenu(serial);

        Account accountToRemove = runTimeBankSystem.findAccount(serial);

        if(accountToRemove == null)
        {
            System.out.println("Account doesn't exist.");

        }
        else
        {
            User user = runTimeBankSystem.findUser(accountToRemove.getId());

            user.removeAccount(accountToRemove);
            runTimeBankSystem.removeAccount(accountToRemove);
            System.out.println("Account removed.");

        }


    }


    /**
     * the method checks the entered amount by the user to see if it is invalid or not
     * @param amount the amount to be checked
     * @return if amount is less or equal to 0 it is invalid
     */
    public boolean invalidAmount(int amount)
    {
        return amount == 0 || amount < 0;

    }


    public static void main(String[] args)
    {
        Run run = new Run();



        run.printMainMenu();
    }

}