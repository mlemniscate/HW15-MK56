package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.Account;
import ir.maktab.repository.AccountRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

public class AccountRepositoryImpl extends BaseEntityRepositoryImpl<Account, Long> implements AccountRepository {

    public AccountRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        List<Account> accounts = getEntityManager().createQuery(
                " from Account a where a.accountNumber = :number", Account.class
        ).setParameter("number", accountNumber).getResultList();
        if (Objects.isNull(accounts)) return null;
        else return accounts.get(0);
    }

    @Override
    public List<Account> getAllCustomerAccounts(Long customerId) {
        return getEntityManager().createNamedQuery("getAllCustomerAccounts", Account.class)
                .setParameter(1, customerId)
                .getResultList();
    }
}
