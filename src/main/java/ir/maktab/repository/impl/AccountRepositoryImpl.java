package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.Account;

import javax.persistence.EntityManager;

public class AccountRepositoryImpl extends BaseEntityRepositoryImpl<Account, Long> {

    public AccountRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }
}
