package ir.maktab.repository;

import ir.maktab.base.repository.BaseEntityRepository;
import ir.maktab.domain.Account;

import java.util.List;

public interface AccountRepository extends BaseEntityRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);

    List<Account> getAllCustomerAccounts(Long customerId);
}
