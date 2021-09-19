package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.BankCEO;
import ir.maktab.repository.BankCEORepository;
import ir.maktab.service.BankCEOService;

public class BankCEOServiceImpl extends BaseEntityServiceImpl<BankCEO, Long, BankCEORepository> implements BankCEOService {

    public BankCEOServiceImpl(BankCEORepository repository) {
        super(repository);
    }
}
