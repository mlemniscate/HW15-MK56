package ir.maktab.service.impl;


import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.Transaction;
import ir.maktab.repository.TransactionRepository;
import ir.maktab.service.TransactionService;

public class TransactionServiceImpl extends BaseEntityServiceImpl<Transaction, Long, TransactionRepository> implements TransactionService {

    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }
}
