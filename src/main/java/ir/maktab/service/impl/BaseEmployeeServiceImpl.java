package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.BaseEmployee;
import ir.maktab.repository.BaseEmployeeRepository;
import ir.maktab.service.BaseEmployeeService;

import java.util.Objects;

public class BaseEmployeeServiceImpl extends BaseEntityServiceImpl<BaseEmployee, Long, BaseEmployeeRepository> implements BaseEmployeeService {

    public BaseEmployeeServiceImpl(BaseEmployeeRepository repository) {
        super(repository);
    }

    @Override
    public BaseEmployee login(String username, String password) {
        BaseEmployee employee = repository.findByUsername(username);
        if(!Objects.isNull(employee) && employee.getPassword().equals(password)) return employee;
        else return null;
    }
}
