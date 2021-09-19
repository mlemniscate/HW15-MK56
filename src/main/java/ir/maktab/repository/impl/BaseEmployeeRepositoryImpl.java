package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.BaseEmployee;

import javax.persistence.EntityManager;

public class BaseEmployeeRepositoryImpl extends BaseEntityRepositoryImpl<BaseEmployee, Long> {

    public BaseEmployeeRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<BaseEmployee> getEntityClass() {
        return BaseEmployee.class;
    }
}
