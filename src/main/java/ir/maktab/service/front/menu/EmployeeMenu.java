package ir.maktab.service.front.menu;

import com.github.javafaker.Faker;
import ir.maktab.domain.*;
import ir.maktab.domain.enums.AccountType;
import ir.maktab.service.front.input.InputInt;
import ir.maktab.service.front.input.InputString;
import ir.maktab.util.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EmployeeMenu extends Menu implements RunnableMenu<Void>{

    private final BaseEmployee loginEmployee;
    private final Branch employeeBranch;

    public EmployeeMenu(BaseEmployee loginEmployee) {
        super(new ArrayList<>(Arrays.asList(
                "Add Account",
                "Deposit Balance",
                "Delete Account",
                "Show Account",
                "Show All Accounts of a Customer",
                "Delete CreditCard",
                "Add CreditCard",
                "Show CreditCard",
                "Close")));
        this.loginEmployee = loginEmployee;
        this.employeeBranch = loginEmployee.getBranch();
    }

    @Override
    public Void runMenu() {
        while (true) {
            switch (getItemFromConsole()) {
                case 1:
                    addAccount();
                    break;
                case 2:
                    depositBalance();
                    break;
                case 3:
                    deleteAccount();
                    break;
                case 4:
                    showAccount();
                    break;
                case 5:
                    showAllCustomerAccounts();
                    break;
                case 6:
                    deleteCreditCard();
                    break;
                case 7:
                    addCreditCard();
                    break;
                case 8:
                    showCreditCard();
                    break;
                case 9:
                    if (new CheckMenu("Are you sure you want to exit?").runMenu()) return null;
                    else break;
            }
        }
    }

    private void showCreditCard() {
        String creditCardNumber = getCreditCardNumber();
        if(ApplicationContext.getCreditCardService().existsByCardNumber(creditCardNumber)){
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
        if(!Objects.isNull(account)) {
            if(account.getCreditCart() == null) {
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
        if(!Objects.isNull(account)) {
            CreditCard creditCart = account.getCreditCart();
            if(creditCart != null) {
                account.setCreditCart(null);
                ApplicationContext.getAccountService().save(account);
                ApplicationContext.getCreditCardService().delete(creditCart);
            } else {
                System.out.println("This account has not a credit card.");
            }
        } else {
            System.out.println("Your ");
        }
    }

    private void showAllCustomerAccounts() {
        Long customerId = getCustomerId();
        List<Account> accountList = ApplicationContext.getAccountService().getAllCustomerAccounts(customerId);
        accountList.forEach(System.out::println);
    }

    private void showAccount() {
        Account account = ApplicationContext.getAccountService().findByAccountNumber(getAccountNumber());
        if(!Objects.isNull(account)) {
            System.out.println(account);
        } else {
            System.out.println("Your account number isn't valid.");
        }
    }

    private void deleteAccount() {
        Account account = ApplicationContext.getAccountService().findByAccountNumber(getAccountNumber());
        if(!Objects.isNull(account)) {
            ApplicationContext.getAccountService().delete(account);
        } else {
            System.out.println("Your account number isn't valid.");
        }
    }

    private void depositBalance() {
        Account account = ApplicationContext.getAccountService().findByAccountNumber(getAccountNumber());
        if(!Objects.isNull(account)) {
            int balance = getBalance();
            account.setBalance(account.getBalance() + balance);
            ApplicationContext.getAccountService().save(account);
        } else {
            System.out.println("Your account number isn't valid.");
        }
    }

    private void addAccount() {
        Account account = getAccount();
        employeeBranch.getAccountList().add(account);
        ApplicationContext.getBranchService().save(employeeBranch);
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
                .build();
    }

    private Customer buildCustomer(Faker faker) {
        Customer customer = new Customer(
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
        ApplicationContext.getCustomerService().save(customer);
        return customer;
    }

    private Integer getBalance() {
        return new InputInt("Enter your balance: ", 1_000_000, 30_000,null )
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
