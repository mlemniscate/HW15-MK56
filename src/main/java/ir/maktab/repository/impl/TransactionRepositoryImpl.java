package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.Transaction;
import ir.maktab.repository.TransactionRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionRepositoryImpl extends BaseEntityRepositoryImpl<Transaction, Long> implements TransactionRepository {

    public TransactionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Transaction> getEntityClass() {
        return Transaction.class;
    }

    @Override
    public List<Transaction> getTransactionsFromTo(String accountNumber, LocalDateTime fromDate, LocalDateTime toDate) {
        return getEntityManager().createQuery("select  t from Transaction t " +
                "where t.account.accountNumber = :account_number and " +
                "t.date > :from_date and t.date < :to_date", Transaction.class)
                .setParameter("account_number", accountNumber)
                .setParameter("from_date", fromDate)
                .setParameter("to_date", toDate)
                .getResultList();
    }
}
