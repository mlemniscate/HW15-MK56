package ir.maktab.repository;

import ir.maktab.base.repository.BaseEntityRepository;
import ir.maktab.domain.Account;

public interface AccountRepository extends BaseEntityRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}
