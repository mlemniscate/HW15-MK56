package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.BankCEO;

import javax.persistence.EntityManager;

public class BankCEORepositoryImpl extends BaseEntityRepositoryImpl<BankCEO, Long> {

    public BankCEORepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<BankCEO> getEntityClass() {
        return BankCEO.class;
    }
}
