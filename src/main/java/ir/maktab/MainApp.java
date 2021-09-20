package ir.maktab;

import com.github.javafaker.Faker;
import ir.maktab.domain.BankCEO;
import ir.maktab.domain.Branch;
import ir.maktab.domain.Employee;
import ir.maktab.domain.PersonInfo;
import ir.maktab.domain.enums.EmployeeRole;
import ir.maktab.service.front.menu.FirstMenu;
import ir.maktab.util.ApplicationContext;
import ir.maktab.util.HibernateUtil;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class MainApp {
    public static void main(String[] args) {
        HibernateUtil.getMainEntityManagerFactory().createEntityManager();
//        enterSomeEmployees();
        new FirstMenu().runMenu();
    }

    private static void enterSomeEmployees() {
        Faker faker = new Faker();
        BankCEO bankCEO = buildBankCEOFaker(faker);
        Branch branch = buildBranchFaker(faker);
        Employee employee1 = buildEmployeeFaker(faker);
        employee1.setUsername("userfake");
        employee1.setBranch(branch);
        branch.getEmployees().add(employee1);
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
