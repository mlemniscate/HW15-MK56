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
        return getEntityManager().createQuery("select a from Account a where a.customer.id = :id", Account.class)
                .setParameter("id", customerId)
                .getResultList();
    }

    @Override
    public Account getByCardId(Long cardId) {
        List<Account> accounts = getEntityManager().createQuery("select a from Account a where a.creditCart.id = :credit", Account.class)
                .setParameter("credit", cardId)
                .getResultList();
        if (!Objects.isNull(accounts)) return accounts.get(0);
        else return null;
    }
}
