package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.BaseEmployee;
import ir.maktab.repository.BaseEmployeeRepository;
import ir.maktab.service.BaseEmployeeService;

public class BaseEmployeeServiceImpl extends BaseEntityServiceImpl<BaseEmployee, Long, BaseEmployeeRepository> implements BaseEmployeeService {

    public BaseEmployeeServiceImpl(BaseEmployeeRepository repository) {
        super(repository);
    }
}
