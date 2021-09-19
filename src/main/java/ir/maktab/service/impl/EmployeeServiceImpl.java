package ir.maktab.service.impl;


import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.Employee;
import ir.maktab.repository.EmployeeRepository;
import ir.maktab.service.EmployeeService;

public class EmployeeServiceImpl extends BaseEntityServiceImpl<Employee, Long, EmployeeRepository> implements EmployeeService {

    public EmployeeServiceImpl(EmployeeRepository repository) {
        super(repository);
    }
}
