package ir.maktab.service;

import ir.maktab.base.service.BaseEntityService;
import ir.maktab.domain.BaseEmployee;

public interface BaseEmployeeService extends BaseEntityService<BaseEmployee, Long> {
    BaseEmployee login(String username, String password);
}
