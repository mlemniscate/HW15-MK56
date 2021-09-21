package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.Account;
import ir.maktab.repository.AccountRepository;
import ir.maktab.service.AccountService;

public class AccountServiceImpl extends BaseEntityServiceImpl<Account, Long, AccountRepository> implements AccountService {

    public AccountServiceImpl(AccountRepository repository) {
        super(repository);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }
}
