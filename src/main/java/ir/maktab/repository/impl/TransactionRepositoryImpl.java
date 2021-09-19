package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.Transaction;

import javax.persistence.EntityManager;

public class TransactionRepositoryImpl extends BaseEntityRepositoryImpl<Transaction, Long> {

    public TransactionRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Transaction> getEntityClass() {
        return Transaction.class;
    }
}
