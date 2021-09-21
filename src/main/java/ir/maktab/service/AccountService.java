package ir.maktab.service;

import ir.maktab.base.service.BaseEntityService;
import ir.maktab.domain.Account;

import java.util.List;

public interface AccountService extends BaseEntityService<Account, Long> {
    Account findByAccountNumber(String accountNumber);

    List<Account> getAllCustomerAccounts(Long customerId);

    Account getByCardId(Long cardId);
}
