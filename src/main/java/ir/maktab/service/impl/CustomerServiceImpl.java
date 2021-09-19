package ir.maktab.service.impl;


import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.Customer;
import ir.maktab.repository.CustomerRepository;
import ir.maktab.service.CustomerService;

public class CustomerServiceImpl extends BaseEntityServiceImpl<Customer, Long, CustomerRepository> implements CustomerService {

    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }
}
