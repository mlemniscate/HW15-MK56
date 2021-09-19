package ir.maktab.util;

import ir.maktab.repository.*;
import ir.maktab.repository.impl.*;
import ir.maktab.service.*;
import ir.maktab.service.impl.*;

import javax.persistence.EntityManager;

public class ApplicationContext {

    private static final AccountRepository accountRepository;
    private static final BankCEORepository bankCEORepository;
    private static final BaseEmployeeRepository baseEmployeeRepository;
    private static final BranchRepository branchRepository;
    private static final CreditCardRepository creditCardRepository;
    private static final CustomerRepository customerRepository;
    private static final EmployeeRepository employeeRepository;
    private static final TransactionRepository transactionRepository;

    private static final AccountService accountService;
    private static final BankCEOService bankCEOService;
    private static final BaseEmployeeService baseEmployeeService;
    private static final BranchService branchService;
    private static final CreditCardService creditCardService;
    private static final CustomerService customerService;
    private static final EmployeeService employeeService;
    private static final TransactionService transactionService;

    static {
        EntityManager entityManager = HibernateUtil.getMainEntityManagerFactory().createEntityManager();
        accountRepository = new AccountRepositoryImpl(entityManager);
        bankCEORepository = new BankCEORepositoryImpl(entityManager);
        baseEmployeeRepository = new BaseEmployeeRepositoryImpl(entityManager);
        branchRepository = new BranchRepositoryImpl(entityManager);
        creditCardRepository = new CreditCardRepositoryImpl(entityManager);
        customerRepository = new CustomerRepositoryImpl(entityManager);
        employeeRepository = new EmployeeRepositoryImpl(entityManager);
        transactionRepository = new TransactionRepositoryImpl(entityManager);
        accountService = new AccountServiceImpl(accountRepository);
        bankCEOService = new BankCEOServiceImpl(bankCEORepository);
        baseEmployeeService = new BaseEmployeeServiceImpl(baseEmployeeRepository);
        branchService = new BranchServiceImpl(branchRepository);
        creditCardService = new CreditCardServiceImpl(creditCardRepository);
        customerService = new CustomerServiceImpl(customerRepository);
        employeeService = new EmployeeServiceImpl(employeeRepository);
        transactionService = new TransactionServiceImpl(transactionRepository);
    }

    public static AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public static BankCEORepository getBankCEORepository() {
        return bankCEORepository;
    }

    public static BaseEmployeeRepository getBaseEmployeeRepository() {
        return baseEmployeeRepository;
    }

    public static BranchRepository getBranchRepository() {
        return branchRepository;
    }

    public static CreditCardRepository getCreditCardRepository() {
        return creditCardRepository;
    }

    public static CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public static EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public static TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public static AccountService getAccountService() {
        return accountService;
    }

    public static BankCEOService getBankCEOService() {
        return bankCEOService;
    }

    public static BaseEmployeeService getBaseEmployeeService() {
        return baseEmployeeService;
    }

    public static BranchService getBranchService() {
        return branchService;
    }

    public static CreditCardService getCreditCardService() {
        return creditCardService;
    }

    public static CustomerService getCustomerService() {
        return customerService;
    }

    public static EmployeeService getEmployeeService() {
        return employeeService;
    }

    public static TransactionService getTransactionService() {
        return transactionService;
    }
}
