import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

public class SimpleBankingApplication {

    private class BankAccount {
        private String accountNumber;
        private String accountHolderName;
        private double balance;

        public BankAccount(String accountHolderName) {
            this.accountHolderName = accountHolderName;
            this.accountNumber = generateAccountNumber();
            this.balance = 0.0;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Successfully deposited " + formatCurrency(amount));
            } else {
                System.out.println("Deposit amount must be greater than 0.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Successfully withdrew " + formatCurrency(amount));
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        }

        public void displayAccountDetails() {
            System.out.println("Account Holder: " + accountHolderName);
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Balance: " + formatCurrency(balance));
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        private String generateAccountNumber() {
            Random random = new Random();
            StringBuilder accountNumber = new StringBuilder();
            for (int i = 0; i < 15; i++) {
                accountNumber.append(random.nextInt(10));
            }
            return accountNumber.toString();
        }

        private String formatCurrency(double amount) {
            Locale indianLocale = new Locale("en", "IN");
            NumberFormat formatter = NumberFormat.getCurrencyInstance(indianLocale);
            return formatter.format(amount); // returns with ₹ sign
        }
    }

    private ArrayList<BankAccount> accounts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SimpleBankingApplication app = new SimpleBankingApplication();
        app.run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== Simple Banking Application =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using the banking application!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        BankAccount newAccount = new BankAccount(name);
        accounts.add(newAccount);
        System.out.println("Account created successfully!");
        newAccount.displayAccountDetails();
    }

    private void depositMoney() {
        BankAccount account = findAccount();
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        }
    }

    private void withdrawMoney() {
        BankAccount account = findAccount();
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        }
    }

    private void checkBalance() {
        BankAccount account = findAccount();
        if (account != null) {
            account.displayAccountDetails();
        }
    }

    private BankAccount findAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        System.out.println("Account not found.");
        return null;
    }
}
