package org.pursuit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

//TODO figure out how to add commas if the balance is more than 3 digits
//TODO check for the 0 in the withdraw and deposit ( if amount = 0, return you have mad no changes in account)
public class BankTeller extends Customer {
    private ArrayList<Customer> allCustomers = new ArrayList<>();
    private HashSet<String> employees = new HashSet<>();

    private Scanner scanner = new Scanner(System.in);
    private ConstantStrings constantStrings = new ConstantStrings();
    private Customer customer = new Customer();


    private String userInputString;
    private int userInputNumber;

    private String tellerPassword = "Abudu";


    public void createCustomer() {
        Customer lola = new Customer();
        lola.setName("Lola");
        lola.addAccount(101, 50000.00);
        lola.addAccount(111, 20000.00);
        allCustomers.add(lola);

        Customer khaing = new Customer();
        khaing.setName("Khaing");
        khaing.addAccount(202, 10000.00);
        allCustomers.add(khaing);

        Customer ben = new Customer();
        ben.setName("Ben");
        ben.addAccount(303, 20000.00);
        ben.addAccount(333, 15000.00);
        allCustomers.add(ben);

        Customer joe = new Customer();
        joe.setName("Joe");
        joe.addAccount(404, 30000.00);
        allCustomers.add(joe);

        Customer sanje = new Customer();
        sanje.setName("Sanje");
        sanje.addAccount(505, 40000.00);
        sanje.addAccount(555, 10000.00);
        allCustomers.add(sanje);

        employees.add("Employee1");
        employees.add("Employee2");
        employees.add("Employee3");

    }

    public void logo() {
        String[] logo = {" _           _ _ _        _       ____              _    \n" +
                "| |         | (_) |      ( )     |  _ \\            | |   \n" +
                "| |     ___ | |_| |_ __ _|/ ___  | |_) | __ _ _ __ | | __\n" +
                "| |    / _ \\| | | __/ _` | / __| |  _ < / _` | '_ \\| |/ /\n" +
                "| |___| (_) | | | || (_| | \\__ \\ | |_) | (_| | | | |   < \n" +
                "|______\\___/|_|_|\\__\\__,_| |___/ |____/ \\__,_|_| |_|_|\\_\\"};
        for (int i = 0; i < logo.length; i++) {
            System.out.println(logo[0] + "\n");
            tellerUsernamePassword();
        }
    }

    private void tellerUsernamePassword() {
        userInputString = "";
        String password;
        String tellerUsername = "Lola";

        for (int i = 0; i < 5; i++) {
            System.out.println("Please enter your username");
            userInputString = scanner.nextLine();
            System.out.println("Please enter your password");
            password = scanner.nextLine();
            if (userInputString.equals(tellerUsername) && password.equals(tellerPassword)) {
                startProgram();
            } else {
                System.out.println("Username and/or password NOT correct");
            }
        }
    }

    public void startProgram() {
        System.out.println("\n" + "Please select <1-6>");
        System.out.println(constantStrings.option1);
        do {
            //try{
                userInputNumber = scanner.nextInt();
                if (userInputNumber == 1) {
                    storesAllCustomersNamesOnly();
                } else if (userInputNumber == 2) {
                    showAllCustomersAndAccounts();
                } else if (userInputNumber == 3) {
                    openNewAccount();
                } else if (userInputNumber == 4) {
                    addNewAccount();
                } else if (userInputNumber == 5) {
                    deleteOrRemoveAccount();
                } else {
                    System.out.println("Please enter your choice [1-6]");
                }
//            }catch(InputMismatchException e) {
//                scanner.nextLine();
//                System.out.println("Letters not accepted, enter a number");
//            }
        } while (userInputNumber != 6);
        logout();
    }

    public void storesAllCustomersNamesOnly() {
        System.out.println("\nAll customers: ");
        for (Customer customer : allCustomers) {
            System.out.println(customer.getName());
        }
        startProgram();
    }

    public void showAllCustomersAndAccounts() {
        System.out.println("Please re-enter password");
        do {
            userInputString = scanner.nextLine();
            if (userInputString.equals(tellerPassword)) {
                for (Customer customer : allCustomers) {
                    System.out.println("Customer: " + customer.getName());
                    System.out.println("Accounts: " + "$" + customer.getAccounts() + "\n");
                }
                withdrawOrDeposit();
            }
        } while (!userInputString.equals(tellerPassword));
    }

    private void addNewAccount() {
        int accountNum;
        int initialBalance;
        System.out.println("Enter name of customer to add new account");
        do {
            userInputString = scanner.nextLine();
            for (Customer customer : allCustomers) {
                if (customer.getName().equals(userInputString)) {
                    System.out.println("Please enter assigned account number");
                    accountNum = scanner.nextInt();
                    System.out.println("Please enter users initial deposit amount");
                    initialBalance = scanner.nextInt();
                    customer.addAccount(accountNum, initialBalance);
                    System.out.println("Account: " + accountNum + " has been added to " + customer.getName() + "'s accounts");
                    System.out.println(customer.getAccounts());
                    startProgram();
                }
            }
        } while (true);
    }

    private void withdrawOrDeposit() {
        int amount;
        do {
            System.out.println("Which account to withdraw or deposit (Enter 3 digit account number)");
            userInputNumber = scanner.nextInt();

            for (Customer customer : allCustomers) {
                if (customer.getAccounts().containsKey(userInputNumber)) {
                    do {
                        System.out.println("Do you want to: \n1) Withdraw or \n2) Deposit?");
                        amount = scanner.nextInt();
                        if (amount == 1) {
                            System.out.println("How much to withdraw?");
                            amount = scanner.nextInt();
                            System.out.println(customer.getName() + "'s account balance: $" + customer.withdrawl(amount, userInputNumber));
                            startProgram();
                        } else if (amount == 2) {
                            System.out.println("How much to deposit?");
                            amount = scanner.nextInt();
                            System.out.println(customer.getName() + "'s account balance: $" + customer.deposit(amount, userInputNumber));
                            startProgram();
                        }
                    } while (amount != 1 && amount != 2);
                }
            }
        } while (!customer.getAccounts().containsKey(userInputNumber));
    }

    public void openNewAccount() {
        Customer newCustomer = new Customer();
        String newCustomerName;
        int accountNum;
        int newCustomerBalance;

        do {
            System.out.println("Please enter customer's name");
            scanner.nextLine();
            newCustomerName = scanner.nextLine();
            newCustomer.setName(newCustomerName);

            System.out.println("Please enter 3 digit assigned account number");
            accountNum = scanner.nextInt();

            System.out.println("Please enter users initial deposit amount");
            newCustomerBalance = scanner.nextInt();

            newCustomer.addAccount(accountNum, newCustomerBalance);

            allCustomers.add(newCustomer);
            System.out.println(newCustomerName + " has been added in the system");
            System.out.println("Account " + accountNum + ":" + " $" + newCustomerBalance);
            System.out.println("Transaction completed!" + "\n");

            startProgram();
        } while (newCustomerName.equals(""));
    }

    public void deleteOrRemoveAccount() {
        System.out.println(constantStrings.option2);
        userInputNumber = scanner.nextInt();
        if (userInputNumber == 1) {
            deleteEntireCustomerAccounts();
        } else if (userInputNumber == 2) {
            removeOneCustomerAccount();
        } else {
            System.out.println("Please select [1/2]");
        }
    }

    public void deleteEntireCustomerAccounts() {
        System.out.println("Enter customer name");
        scanner.nextLine();
        userInputString = scanner.nextLine();

        for (int i = 0; i < allCustomers.size(); i++) {
            if (allCustomers.get(i).getName().equals(userInputString)) {
                allCustomers.remove(i);
                //System.out.println();
            }
        }
        System.out.println("All customers: ");
        for (Customer customer2 : allCustomers) {
            System.out.println(customer2.getName());
        }
        startProgram();
    }

    public void removeOneCustomerAccount() {
        System.out.println("Enter customer name");
        scanner.nextLine();
        userInputString = scanner.nextLine();

        for (Customer customer : allCustomers) {
            if (customer.getName().equals(userInputString)) {
                System.out.println(customer.getAccounts());
                System.out.println("Enter account 3 digit account number to be removed");
                userInputNumber = scanner.nextInt();
                customer.deleteAccount(userInputNumber);
            }
        }
        startProgram();
    }

    private void logout() {
        System.out.println("Enter 'yes' to continue logging out");
        do {
            userInputString = scanner.nextLine();
            if (userInputString.equalsIgnoreCase("yes")) {
                System.out.println("Successfully logged out!");
                System.out.println("To log back in...");
                tellerUsernamePassword();
            }
        }while(!userInputString.equalsIgnoreCase("yes"));
    }
}
