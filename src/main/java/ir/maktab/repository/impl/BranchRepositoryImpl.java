package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.Branch;

import javax.persistence.EntityManager;

public class BranchRepositoryImpl extends BaseEntityRepositoryImpl<Branch, Long> {

    public BranchRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Branch> getEntityClass() {
        return Branch.class;
    }
}
