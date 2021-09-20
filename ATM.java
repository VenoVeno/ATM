import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Veno_MSc {
    public static class ATM {
        // TOTAL AMOUNT AVAILABLE IN ATM
        private int amount = 0;

        // INTIAL DENOMINATION AVAILABLE AT ATM
        private Map<Integer, Integer> denomination = new HashMap<>();
        private int denominationSum = 0;

        // CURRENT DENOMINATION AVAILABLE AT LATEST
        private Map<Integer, Integer> currentDenomination = new HashMap<>();

        // MIN LIMIT AND MAX LIMIT PER TRANSACTION FOR WITHDRAW
        private int minWithdrawLimit = 100, maxWithdrawLimit = 10000;

        // MIN LIMIT AND MAX LIMIT PER TRANSACTION FOR TRANSFER
        private int minTransferLimit = 1000, maxTransferLimit = 10000;

        // TO INITIALISE DENOMINATION AND ITS COUNT
        // ALSO TO UPDATE INITIAL ATM AMOUNT TOTAL
        public ATM() {
            this.denomination.put(1000, 20);
            this.denomination.put(500, 100);
            this.denomination.put(100, 300);

            this.denominationSum = putInitDenominationSum();

            this.currentDenomination = putCurrentDenomination();
        }

        public Map<Integer, Integer> putCurrentDenomination() {
            Map<Integer, Integer> denom = new HashMap<>();

            for (Map.Entry mEntry : this.denomination.entrySet())
                denom.put((int) mEntry.getKey(), 0);

            return denom;
        }

        // GET THE AMOUNT AVAILABLE IN THE ATM
        public int getAmount() {
            // System.out.println("Amount available in ATM " + this.amount);
            return this.amount;
        }

        // INITIALISE INITIAL DENOMINATION SUM
        public int putInitDenominationSum() {
            int sum = 0;
            for (Map.Entry mEntry : this.denomination.entrySet()) {
                sum += (int) mEntry.getKey() * (int) mEntry.getValue();
            }
            return sum;
        }

        // GET INIT DENOMINATION SUM THAT WAS AVAILABLE WHILE OPENING ATM
        public int getInitDenominationSum() {
            return this.denominationSum;
        }

        // LOAD CASH INTO ATM
        public void loadCash(int nLakhs) {
            int initAmount = this.amount;

            System.out.println("Before Loading Cash Into ATM : " + this.amount);
            this.availableDenominations();

            for (Map.Entry mEntry : this.denomination.entrySet()) {
                // WE INCREMENT EACH DENOM COUNT BY ADDING TO EXISTING
                this.currentDenomination.put((int) mEntry.getKey(),
                        this.currentDenomination.get((int) mEntry.getKey()) + (int) mEntry.getValue() * nLakhs);
            }

            this.amount += nLakhs * this.getInitDenominationSum();

            System.out.println(
                    "+(" + (this.amount - initAmount) + ") After Loading Cash Into ATM : " + this.amount + "\n");
            this.availableDenominations();
        }

        public void availableDenominations() {
            System.out.println("Available Denominations as");

            for (Map.Entry mEntry : this.currentDenomination.entrySet()) {
                int denom = (int) mEntry.getKey();
                int countDenom = (int) mEntry.getValue();

                System.out.println(denom + " * " + countDenom + " = " + denom * countDenom);
            }

            System.out.println();
        }

        public int getMinWithdrawLimit() {
            return this.minWithdrawLimit;
        }

        public int getMaxWithdrawLimit() {
            return this.maxWithdrawLimit;
        }

        public int getMinTransferLimit() {
            return this.minTransferLimit;
        }

        public int getMaxTransferLimit() {
            return this.maxTransferLimit;
        }
    }

    public static class CustomerDetails {
        int accountNumber, pin, accountBalance;
        String name;

        CustomerDetails(int accountNumber, String name, int pin, int accountBalance) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.name = name;
            this.accountBalance = accountBalance;
        }
    }

    public static class MiniStatement {
        int transactionId, transactionAmount;
        String remarks, transactionType;

        MiniStatement(int transactionId, int transactionAmount, String remarks, String transactionType) {
            this.transactionId = transactionId;
            this.transactionAmount = transactionAmount;
            this.remarks = remarks;
            this.transactionType = transactionType;
        }
    }

    // public static class MiniStatements {
    // MiniStatement[] miniStatements = new MiniStatement();

    // MiniStatements() {
    // miniStatements
    // }
    // }

    public static class Customer {
        // Customer Details Map
        // private Map<Integer, List<Object>> customerDetails = new HashMap<>();
        private List<String> cusDetailHeader = new LinkedList<>();

        private CustomerDetails[] customerDetailss = new CustomerDetails[5];

        // MINI STATEMENT
        private Map<Integer, List<List<Object>>> miniStatement = new HashMap<>();
        private List<String> miniStatementHeader = new LinkedList<>();

        // private MiniStatement[] miniStatements = new MiniStatement[5];

        Customer() {

            this.cusDetailHeader.addAll(Arrays.asList("Acc No", "Account Holder", "Pin Number", "Account Balance"));

            // this.customerDetails.put(101, Arrays.asList("Suresh", 2343, 25234));
            // this.customerDetails.put(102, Arrays.asList("Ganesh", 5432, 34123));
            // this.customerDetails.put(103, Arrays.asList("Magesh", 7854, 26100));
            // this.customerDetails.put(104, Arrays.asList("Naresh", 2345, 80000));
            // this.customerDetails.put(105, Arrays.asList("Harish", 1907, 103400));

            this.customerDetailss[0] = new CustomerDetails(101, "Suresh", 2343, 25234);
            this.customerDetailss[1] = new CustomerDetails(102, "Ganesh", 5432, 34123);
            this.customerDetailss[2] = new CustomerDetails(103, "Magesh", 7854, 26100);
            this.customerDetailss[3] = new CustomerDetails(104, "Naresh", 2345, 80000);
            this.customerDetailss[4] = new CustomerDetails(105, "Harish", 1907, 103400);

            // for (int i = 0; i < this.customerDetailss.length; i++) {
            // System.out.println(this.customerDetailss[i].accountBalance);
            // }

            this.miniStatementHeader.addAll(
                    Arrays.asList("Transaction ID", "Transaction Remarks", "Transaction Type", "Transaction Amount"));
        }

        void printCustomerDetail() {
            for (int index = 0; index < this.cusDetailHeader.size(); index++) {
                System.out.print(this.cusDetailHeader.get(index) + "   ");
            }

            System.out.println();

            // for (Map.Entry mEntry : this.customerDetails.entrySet()) {
            // int accountNumber = (int) mEntry.getKey();
            // System.out.print(accountNumber + "\t");

            // List<Object> accDetails = (List<Object>) mEntry.getValue();
            // // System.out.println(accDetails);

            // for (int detail = 0; detail < accDetails.size(); detail++) {
            // System.out.print(accDetails.get(detail) + "\t\t");
            // }

            // System.out.println();
            // }

            for (int i = 0; i < this.customerDetailss.length; i++) {
                System.out.print(this.customerDetailss[i].accountNumber + "\t\t");
                System.out.print(this.customerDetailss[i].name + "\t\t");
                System.out.print(this.customerDetailss[i].pin + "\t\t");
                System.out.print(this.customerDetailss[i].accountBalance + "\t\t");

                System.out.println();
            }

            System.out.println("------------------");
        }

        public boolean validateUser(int accountNumber, int pinNumber) {
            // if (this.customerDetails.isEmpty() == true)
            // return false;
            // else {
            // for (Map.Entry mEntry : this.customerDetails.entrySet()) {
            // int aNumber = (int) mEntry.getKey();
            // // System.out.print("Ac No " + accountNumber);

            // int pNumber = (int) ((List<Object>) mEntry.getValue())
            // .get(this.cusDetailHeader.indexOf("Pin Number") - 1);

            // // System.out.print("Pic No " + pNumber);

            // if (aNumber == accountNumber && pNumber == pinNumber)
            // return true;
            // return false;
            // }
            // return false;
            // }

            if (this.customerDetailss.length == 0)
                return false;

            else {
                for (int i = 0; i < this.customerDetailss.length; i++) {
                    if (this.customerDetailss[i].accountNumber == accountNumber
                            && this.customerDetailss[i].pin == pinNumber)
                        return true;
                }
                return false;
            }
        }

        public String getCustomerName(int accountNumber) {
            // if (this.customerDetails.get(accountNumber) == null)
            // return null;

            // return
            // this.customerDetails.get(accountNumber).get(this.cusDetailHeader.indexOf("Account
            // Holder") - 1)
            // .toString();

            for (int i = 0; i < this.customerDetailss.length; i++) {
                if (this.customerDetailss[i].accountNumber == accountNumber)
                    return customerDetailss[i].name;
            }

            return null;
        }

        public int getAccountBalance(int accountNumber) {
            // if (this.customerDetails.get(accountNumber) == null)
            // return null;

            // return
            // this.customerDetails.get(accountNumber).get(this.cusDetailHeader.indexOf("Account
            // Balance") - 1)
            // .toString();

            for (int i = 0; i < this.customerDetailss.length; i++) {
                if (this.customerDetailss[i].accountNumber == accountNumber)
                    return customerDetailss[i].accountBalance;
            }

            return 0;
        }

        // UPDATE THE ACCOUNT BALANCE BASED ON ACCOUNT NUMBER WITH NEW BALANCE
        public void updateAccountBalance(int accountNumber, int newBalance) {
            // this.customerDetails.get(accountNumber).set(this.cusDetailHeader.indexOf("Account
            // Balance") - 1,
            // newBalance);

            for (int index = 0; index < this.customerDetailss.length; index++) {
                if (this.customerDetailss[index].accountNumber == accountNumber) {
                    this.customerDetailss[index].accountBalance = newBalance;
                }
            }
        }

        // WITHDRAW FUNCTION
        public String withDraw(ATM atm, int accountNumber, int withdrawAmount) {
            if (withdrawAmount < atm.minWithdrawLimit || withdrawAmount > atm.maxWithdrawLimit)
                return "Not within the Range of Withdrawal Limit";

            else if (withdrawAmount > atm.getAmount())
                return "ATM does not have enough money to vend";

            else if (withdrawAmount > getAccountBalance(accountNumber))
                return "Insufficient Account Balance";

            else {
                atm.amount -= withdrawAmount;
                int balance = this.getAccountBalance(accountNumber);
                balance -= withdrawAmount;

                // UPDATE ACCOUNT BALANCE FOR RECEIVER
                this.updateAccountBalance(accountNumber, balance);

                // UPDATE RECEIPT FOR SENDER
                // int transactionId = this.miniStatement.get(accountNumber).size() + 1;
                int transactionId = getTransactionIdInvoice(accountNumber);
                System.out.println("Transaction Id " + transactionId);

                this.updateMiniStatement(accountNumber, Arrays.asList(transactionId,
                        "Debited " + withdrawAmount + " From ATM", "Debit", withdrawAmount));

                return "Transaction Success! Available Balance is " + this.getAccountBalance(accountNumber);
            }
        }

        // TRANSFER TO CUSTOMER
        public String transfer(ATM atm, int senderAccountNumber, int receiverAccountNumber, int transferAmount) {
            if (transferAmount < atm.minTransferLimit || transferAmount > atm.maxTransferLimit)
                return "Not within the Range of Transfer Limit";

            else if (transferAmount > getAccountBalance(senderAccountNumber))
                return "Insufficient Funds to Transfer";

            else if (getCustomerName(receiverAccountNumber) == null)
                return "Invalid Receiver Account";

            else {
                int senderBalance = this.getAccountBalance(senderAccountNumber);
                senderBalance -= transferAmount;

                int receiverBalance = this.getAccountBalance(receiverAccountNumber);
                receiverBalance += transferAmount;

                // UPDATE ACCOUNT BALANCE FOR SENDER
                this.updateAccountBalance(senderAccountNumber, senderBalance);

                // UPDATE RECEIPT FOR SENDER
                // int transactionId = this.miniStatement.get(senderAccountNumber).size() + 1;
                int transactionId = getTransactionIdInvoice(senderAccountNumber);

                this.updateMiniStatement(senderAccountNumber, Arrays.asList(transactionId,
                        "Funds transfer to Acc" + receiverAccountNumber, "Debit", transferAmount));

                // UPDATE ACCOUNT BALANCE FOR RECEIVER
                this.updateAccountBalance(receiverAccountNumber, receiverBalance);

                // UPDATE RECEIPT FOR RECEIVER
                // transactionId = this.miniStatement.get(receiverAccountNumber).size() + 1;
                transactionId = getTransactionIdInvoice(receiverAccountNumber);

                this.updateMiniStatement(receiverAccountNumber, Arrays.asList(transactionId,
                        "Funds transfer from Acc" + senderAccountNumber, "Credit", transferAmount));

                this.displayMiniStatement(senderAccountNumber, 101);
                this.displayMiniStatement(receiverAccountNumber, 101);

                return "Transfer Successfull";
            }

        }

        public int getTransactionIdInvoice(int accountNumber) {
            if (this.miniStatement.get(accountNumber) == null)
                return 1;
            else {
                int size = this.miniStatement.get(accountNumber).size();
                return Integer.parseInt(this.miniStatement.get(accountNumber).get(size - 1)
                        .get(this.miniStatementHeader.indexOf("Transaction ID")).toString()) + 1;
            }
        }

        // UPDATE MINI STATEMENT
        public void updateMiniStatement(int accountNumber, List<Object> list) {
            System.out.println(this.miniStatement);
            System.out.println(list);
            System.out.println(accountNumber);

            // System.out.println(this.miniStatement.get(accountNumber));

            // IN CASE OF FIRST BILL PUSH INSERT EMPTY LIST
            if (this.miniStatement.get(accountNumber) == null)
                this.miniStatement.put(accountNumber, new ArrayList<List<Object>>(Arrays.asList()));

            // HAVE ONLY THE LAST 5 TRANSACTION
            if (this.miniStatement.get(accountNumber).size() > 5) {
                this.miniStatement.get(accountNumber).remove(0);
            }

            // ADD THE CURRENT STATEMENT INFO TO THE MINI-STATEMENT
            this.miniStatement.get(accountNumber).add(list);

            this.displayMiniStatement(accountNumber, 101);
        }

        public void displayMiniStatement(int accountNumber, int pin) {
            System.out.println("\n------------------\n");

            System.out.println("\nAccount Number : " + accountNumber);
            System.out.println("Account Holder : " + this.getCustomerName(accountNumber));
            System.out.println("Account Balance : " + this.getAccountBalance(accountNumber));

            System.out.println();

            if (this.miniStatement.get(accountNumber).size() == 0) {
                System.out.println("\nNo Past Transaction History Found!");
            } else {

                System.out.println("\t\t MINI STATEMENT\n");

                for (int index = 0; index < this.miniStatementHeader.size(); index++) {
                    System.out.print(this.miniStatementHeader.get(index) + "\t");
                }

                System.out.println();

                List<List<Object>> statementDetails = this.miniStatement.get(accountNumber);

                for (int detail = 0; detail < statementDetails.size(); detail++) {
                    for (int field = 0; field < statementDetails.get(detail).size(); field++)
                        System.out.print(statementDetails.get(detail).get(field).toString() + "\t\t");
                    System.out.println();
                }
            }

            System.out.println("\n------------------");
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        Customer customer = new Customer();

        Scanner scanner = new Scanner(System.in);
        int menuInput = 0;

        while (menuInput != 4) {
            System.out.print(
                    "\n\nMENU\n 1) LOAD CASH\n 2) SHOW CUSTOMER DETAILS\n 3) ATM Process\n Select Your Choice :) ");
            menuInput = scanner.nextInt();

            switch (menuInput) {
                case 1:
                    System.out.println("\n\t\tWe are about to Load Amount into ATM");

                    System.out.print("How many " + atm.getInitDenominationSum() + " would you wish to Deposit? ");
                    int nLakhs = scanner.nextInt();

                    atm.loadCash(nLakhs);

                    break;

                case 2:
                    System.out.println("\n\t\tWe are about to See the List of Customers");
                    customer.printCustomerDetail();

                    break;

                case 3:
                    System.out.println("\n\t\tWe are about to Gone Through the ATM Process");

                    System.out.print("Please Enter Your Account Number : ");
                    int accountNumber = scanner.nextInt();

                    System.out.print("Please Enter Your Pin Number : ");
                    int pinNumber = scanner.nextInt();

                    if (customer.validateUser(accountNumber, pinNumber)) {
                        System.out.println("\nHey " + customer.getCustomerName(accountNumber) + " :>)");
                        int processChoice = 0;

                        while (processChoice != 5) {
                            System.out.print(
                                    "\n MENU\n 1) CHECK BALANCE\n 2) WITHDRAW MONEY\n 3) TRANSFER MONEY\n 4) MINI STATEMENT\n 5) EXIT\n Select Your Choice :) ");
                            processChoice = scanner.nextInt();

                            switch (processChoice) {
                                case 1:
                                    System.out.println("\nBalance as on Latest Update : "
                                            + customer.getAccountBalance(accountNumber));
                                    break;

                                case 2:
                                    System.out.print("Please Enter The Amount to Withdraw : ");
                                    int withdrawAmount = scanner.nextInt();

                                    System.out.println("\n" + customer.withDraw(atm, accountNumber, withdrawAmount));
                                    break;

                                case 3:
                                    System.out.println("\n\t\tWe Are about to Transact The Money");

                                    System.out.print("\nEnter Receiver Account Number : ");
                                    int receiverAccountNumber = scanner.nextInt();

                                    System.out.print("Please Enter The Amount to Transfer : ");
                                    int transferAmount = scanner.nextInt();

                                    System.out.println("\n" + customer.transfer(atm, accountNumber,
                                            receiverAccountNumber, transferAmount));

                                    System.out.println();

                                    customer.printCustomerDetail();

                                    break;

                                case 4:
                                    System.out.println("\n\t\tWe Are about to View Mini Statement");

                                    System.out.print("\nPlease Enter  Account Number : ");
                                    int miniStatementAccountNumber = scanner.nextInt();

                                    System.out.print("Please Enter Your Pin Number : ");
                                    int miniStatementAccountPin = scanner.nextInt();

                                    customer.displayMiniStatement(miniStatementAccountNumber, miniStatementAccountPin);

                                    break;

                                default:
                                    System.out.println("Invalid Input :)");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("\nIncorrect Account Number or Pin! Please Check and Try Again :( ");
                    }
                    break;

                case 4:
                    System.out.println("\nThanks for Using Our Service! Hope You Liked :) :) :)");
                    break;

                default:
                    System.out.println("Invalid Input :)");
                    break;
            }
        }

        scanner.close();
    }
}
