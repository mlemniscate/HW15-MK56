package ir.maktab;

import com.github.javafaker.Faker;
import ir.maktab.domain.*;
import ir.maktab.domain.enums.AccountType;
import ir.maktab.domain.enums.EmployeeRole;
import ir.maktab.service.front.menu.FirstMenu;
import ir.maktab.util.ApplicationContext;
import ir.maktab.util.HibernateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class MainApp {
    public static void main(String[] args) {
        HibernateUtil.getMainEntityManagerFactory().createEntityManager();
        enterSomeEmployees();
        enterSomeEmployees();
        enterSomeCustomerAccountCreditCard();
        new FirstMenu().runMenu();
    }

    private static void enterSomeCustomerAccountCreditCard() {
        Faker faker = new Faker();
        IntStream.range(1, 11).forEach(item -> {
            Customer customer = buildCustomer(faker);
            Account account1 = buildAccount(faker, customer, AccountType.SAVING);
            Account account2 = buildAccount(faker, customer, AccountType.DEPOSIT);
            ApplicationContext.getAccountService().save(account1);
            ApplicationContext.getAccountService().save(account2);
        });
    }

    private static Account buildAccount(Faker faker, Customer customer, AccountType accountType) {
        return Account.builder()
                .customer(customer)
                .accountType(accountType)
                .accountNumber(faker.number().digits(20))
                .balance(30000)
                .creditCart(
                        CreditCard.builder()
                                .number(faker.number().digits(16))
                                .cvv2(Integer.parseInt(faker.number().digits(4)))
                                .expirationDate(LocalDate.of(2023, 12, 15))
                                .password("1234")
                                .secondPassword("123456")
                                .build()
                )
                .build();
    }

    private static Customer buildCustomer(Faker faker) {
        return Customer.builder()
                .personInfo(
                        PersonInfo
                                .builder()
                                .firstName(faker.name().firstName())
                                .lastName(faker.name().lastName())
                                .address(faker.address().fullAddress())
                                .email(faker.internet().emailAddress())
                                .phoneNumber(faker.phoneNumber().phoneNumber())
                                .nationalCode(faker.code().isbn10())
                                .build()
                )
                .customerNumber(Long.parseLong(faker.number().digits(10)))
                .build();
    }

    private static void enterSomeEmployees() {
        Faker faker = new Faker();
        BankCEO bankCEO = buildBankCEOFaker(faker);
        Branch branch = buildBranchFaker(faker);
//        Employee employee1 = buildEmployeeFaker(faker);
//        employee1.setUsername("userfake");
//        employee1.setBranch(branch);
//        branch.getEmployees().add(employee1);
        IntStream.range(1, 50).forEach(item -> {
            Employee employee = buildEmployeeFaker(faker);
            branch.addEmployee(employee);
        });
        branch.addEmployee(bankCEO);
        ApplicationContext.getBranchService().save(branch);
    }

    private static Branch buildBranchFaker(Faker faker) {
        return Branch.builder()
                .address(faker.address().fullAddress())
                .city(faker.address().cityName())
                .name(faker.name().title())
                .employees(new ArrayList<>())
                .code(faker.code().asin())
                .build();
    }

    private static Employee buildEmployeeFaker(Faker faker) {
        return Employee.employeeBuilder()
                .username(faker.name().username())
                .password("123456")
                .employeeRole(EmployeeRole.NORMAL)
                .personInfo(
                        PersonInfo
                                .builder()
                                .firstName(faker.name().firstName())
                                .lastName(faker.name().lastName())
                                .address(faker.address().fullAddress())
                                .email(faker.internet().emailAddress())
                                .phoneNumber(faker.phoneNumber().phoneNumber())
                                .nationalCode(faker.code().isbn10())
                                .build()
                )
                .build();
    }

    private static BankCEO buildBankCEOFaker(Faker faker) {
        return BankCEO.ceoBuilder()
                .username(faker.name().username())
                .password("123456")
                .employeeRole(EmployeeRole.NORMAL)
                .personInfo(
                        PersonInfo
                                .builder()
                                .firstName(faker.name().firstName())
                                .lastName(faker.name().lastName())
                                .address(faker.address().fullAddress())
                                .email(faker.internet().emailAddress())
                                .phoneNumber(faker.phoneNumber().phoneNumber())
                                .nationalCode(faker.code().isbn10())
                                .build()
                )
                .build();
    }
}
