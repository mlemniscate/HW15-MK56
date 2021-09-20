package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.BaseEmployee;
import ir.maktab.repository.BaseEmployeeRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class BaseEmployeeRepositoryImpl extends BaseEntityRepositoryImpl<BaseEmployee, Long> implements BaseEmployeeRepository {

    public BaseEmployeeRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<BaseEmployee> getEntityClass() {
        return BaseEmployee.class;
    }

    @Override
    public BaseEmployee findByUsername(String username) {
        List<BaseEmployee> employees = getEntityManager().createQuery(" from " + getEntityClass().getSimpleName() +
                " where " + BaseEmployee.USERNAME + " =:username", BaseEmployee.class)
                .setParameter("username", username)
                .getResultList();
        if (employees.size() > 0) return employees.get(0);
        else return null;
    }
}
