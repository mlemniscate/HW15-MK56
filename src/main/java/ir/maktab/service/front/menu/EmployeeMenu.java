package ir.maktab.service.front.menu;

import com.github.javafaker.Faker;
import ir.maktab.domain.*;
import ir.maktab.domain.enums.AccountType;
import ir.maktab.service.front.input.InputInt;
import ir.maktab.service.front.input.InputString;
import ir.maktab.util.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
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
                "Show All Accounts of a Customer")));
        this.loginEmployee = loginEmployee;
        this.employeeBranch = loginEmployee.getBranch();
    }

    @Override
    public Void runMenu() {
        Account account;
        while (true) {
            switch (getItemFromConsole()) {
                case 1:
                    account = getAccount();
                    employeeBranch.getAccountList().add(account);
                    ApplicationContext.getBranchService().save(employeeBranch);
                    break;
                case 2:
                    account = ApplicationContext.getAccountService().findByAccountNumber(getAccountNumber());
                    if(!Objects.isNull(account)) {
                        int balance = getBalance();
                        account.setBalance(account.getBalance() + balance);
                        ApplicationContext.getAccountService().save(account);
                    } else {
                        System.out.println("Your account number isn't valid.");
                    }
                    break;
                case 3:
                    account = ApplicationContext.getAccountService().findByAccountNumber(getAccountNumber());
                    if(!Objects.isNull(account)) {
                        ApplicationContext.getAccountService().delete(account);
                    } else {
                        System.out.println("Your account number isn't valid.");
                    }
                    break;
                case 4:
                    account = ApplicationContext.getAccountService().findByAccountNumber(getAccountNumber());
                    if(!Objects.isNull(account)) {
                        System.out.println(account);
                    } else {
                        System.out.println("Your account number isn't valid.");
                    }
                    break;
                case 10:
                    if (new CheckMenu("Are you sure you want to exit?").runMenu()) return null;
                    else break;
            }
        }
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
                        getCustomerInfo(faker)
                )
                .build();
    }

    private Customer getCustomerInfo(Faker faker) {
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
