package ir.maktab.repository;

import ir.maktab.base.repository.BaseEntityRepository;
import ir.maktab.domain.BaseEmployee;

public interface BaseEmployeeRepository extends BaseEntityRepository<BaseEmployee, Long> {
    BaseEmployee findByUsername(String username);
}
