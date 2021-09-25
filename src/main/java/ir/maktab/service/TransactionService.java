package ir.maktab.service;

import ir.maktab.base.service.BaseEntityService;
import ir.maktab.domain.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService extends BaseEntityService<Transaction, Long> {
    List<Transaction> getTransactionsFromTo(String accountNumber, LocalDateTime fromDate, LocalDateTime toDate);
}
