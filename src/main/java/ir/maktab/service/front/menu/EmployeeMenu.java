package ir.maktab.service.front.menu;

import com.github.javafaker.Faker;
import ir.maktab.domain.*;
import ir.maktab.domain.enums.AccountType;
import ir.maktab.service.front.input.InputInt;
import ir.maktab.service.front.input.InputString;
import ir.maktab.util.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EmployeeMenu extends Menu implements RunnableMenu<Void> {

    private final BaseEmployee loginEmployee;

    public EmployeeMenu(BaseEmployee loginEmployee) {
        super(new ArrayList<>(Arrays.asList(
                "Add Account",
                "Add Account to Existing Customer",
                "Deposit Balance",
                "Delete Account",
                "Show Account",
                "Show All Accounts of a Customer",
                "Delete CreditCard",
                "Add CreditCard",
                "Show CreditCard",
                "Show Transactions",
                "Close")));
        this.loginEmployee = loginEmployee;
    }

    @Override
    public Void runMenu() {
        while (true) {
            switch (getItemFromConsole()) {
                case 1:
                    addAccount();
                    break;
                case 2:
                    addAccountToCustomer();
                    break;
                case 3:
                    depositBalance();
                    break;
                case 4:
                    deleteAccount();
                    break;
                case 5:
                    showAccount();
                    break;
                case 6:
                    showAllCustomerAccounts();
                    break;
                case 7:
                    deleteCreditCard();
                    break;
                case 8:
                    addCreditCard();
                    break;
                case 9:
                    showCreditCard();
                    break;
                case 10:
                    showTransactions();
                    break;
                case 11:
                    if (new CheckMenu("Are you sure you want to exit?").runMenu()) return null;
                    else break;
            }
        }
    }

    private void showTransactions() {
        String accountNumber = getAccountNumber();
        LocalDateTime fromDate = enterDate("start");
        LocalDateTime toDate = enterDate("end");
        List<Transaction> transactions = ApplicationContext.getTransactionService().getTransactionsFromTo(accountNumber, fromDate, toDate);
        transactions.forEach(System.out::println);
    }

    private static LocalDateTime enterDate(String dateName) {
        String stringInput = new InputString("Enter your "+ dateName + " date: ", "^\\d{4}-\\d{1,2}-\\d{1,2}$")
                .getStringInput();
        String[] splitDate = stringInput.split("-");
        return LocalDateTime.of(Integer.parseInt(splitDate[0]),
                Integer.parseInt(splitDate[1]),
                Integer.parseInt(splitDate[2]), 0, 0, 0);
    }

    private void addAccountToCustomer() {
        Customer customer = ApplicationContext.getCustomerService().findById(getCustomerId());
        Account account = getAccount();
        account.setCustomer(customer);
        loginEmployee.getBranch().getAccountList().add(account);
        ApplicationContext.getBaseEmployeeService().save(loginEmployee);
    }

    private void showCreditCard() {
        String creditCardNumber = getCreditCardNumber();
        if (ApplicationContext.getCreditCardService().existsByCardNumber(creditCardNumber)) {
            CreditCard creditCard = ApplicationContext.getCreditCardService().getByCardNumber(creditCardNumber);
            System.out.println(creditCard);
        } else {
            System.out.println("Credit Card number is wrong!");
        }
    }

    private String getCreditCardNumber() {
        return new InputString("Enter credit card number: ").getStringInput();
    }

    private void addCreditCard() {
        Account account = ApplicationContext.getAccountService().findByAccountNumber(getAccountNumber());
        if (!Objects.isNull(account)) {
            if (account.getCreditCart() == null) {
                account.setCreditCart(buildCreditCard());
                ApplicationContext.getAccountService().save(account);
            } else {
                System.out.println("This account has a credit card.");
            }
        } else {
            System.out.println("Your account number is wrong!");
        }
    }

    private void deleteCreditCard() {
        Account account = ApplicationContext.getAccountService().findByAccountNumber(getAccountNumber());
        if (!Objects.isNull(account)) {
            CreditCard creditCart = account.getCreditCart();
            if (creditCart != null) {
                account.setCreditCart(null);
                ApplicationContext.getAccountService().save(account);
                ApplicationContext.getCreditCardService().delete(creditCart);
            } else {
                System.out.println("This account has not a credit card.");
            }
        } else {
            System.out.println("Your account number is wrong!");
        }
    }

    private void showAllCustomerAccounts() {
        Long customerId = getCustomerId();
        List<Account> accountList = ApplicationContext.getAccountService().getAllCustomerAccounts(customerId);
        accountList.forEach(System.out::println);
    }

    private void showAccount() {
        Account account = ApplicationContext.getAccountService().findByAccountNumber(getAccountNumber());
        if (!Objects.isNull(account)) {
            System.out.println(account);
        } else {
            System.out.println("Your account number isn't valid.");
        }
    }

    private void deleteAccount() {
        Account account = ApplicationContext.getAccountService().findByAccountNumber(getAccountNumber());
        if (!Objects.isNull(account)) {
            Customer customer = account.getCustomer();
            loginEmployee.getBranch().getAccountList().remove(account);
            ApplicationContext.getBaseEmployeeService().save(loginEmployee);
            ApplicationContext.getAccountService().delete(account);
            if (ApplicationContext.getAccountService().getAllCustomerAccounts(
                            customer.getId()).size() == 0) {
                ApplicationContext.getCustomerService().delete(customer);
            }
        } else {
            System.out.println("Your account number isn't valid.");
        }
    }

    private void depositBalance() {
        Account account = ApplicationContext.getAccountService().findByAccountNumber(getAccountNumber());
        if (!Objects.isNull(account)) {
            int balance = getBalance();
            account.setBalance(account.getBalance() + balance);
            ApplicationContext.getAccountService().save(account);
        } else {
            System.out.println("Your account number isn't valid.");
        }
    }

    private void addAccount() {
        Account account = getAccount();
        loginEmployee.getBranch().getAccountList().add(account);
        ApplicationContext.getBaseEmployeeService().save(loginEmployee);
    }

    private Long getCustomerId() {
        return Long.parseLong(new InputString("Enter your customer id: ").getStringInput());
    }

    private String getAccountNumber() {
        return new InputString("Enter account number: ").getStringInput();
    }

    private Account getAccount() {
        Faker faker = new Faker();
        return Account.builder()
                .accountNumber(faker.number().digits(20))
                .accountType(getAccountType())
                .balance(getBalance())
                .customer(
                        buildCustomer(faker)
                )
                .creditCart(buildCreditCard()
                )
                .build();
    }

    private CreditCard buildCreditCard() {
        Faker faker = new Faker();
        return CreditCard.builder()
                .number(faker.number().digits(16))
                .cvv2(Integer.parseInt(faker.number().digits(4)))
                .expirationDate(LocalDate.of(2023, 12, 15))
                .password("1234")
                .secondPassword("123456")
                .build();
    }

    private Customer buildCustomer(Faker faker) {
        return new Customer(
                new PersonInfo(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().phoneNumber(),
                        faker.address().fullAddress(),
                        faker.number().digits(10)
                ),
                Long.parseLong(faker.number().digits(12))
        );
    }

    private Integer getBalance() {
        return new InputInt("Enter your balance: ", 1_000_000, 30_000, null)
                .getIntInput();
    }

    private AccountType getAccountType() {
        ArrayList<AccountType> accountTypes = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        for (AccountType accountType : AccountType.values()) {
            list.add(accountType.name());
            accountTypes.add(accountType);
        }
        Menu menu = new Menu(list);
        int itemFromConsole = menu.getItemFromConsole();
        return accountTypes.get(itemFromConsole - 1);
    }
}
