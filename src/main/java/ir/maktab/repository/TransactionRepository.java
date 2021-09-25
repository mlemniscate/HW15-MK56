package ir.maktab.repository;

import ir.maktab.base.repository.BaseEntityRepository;
import ir.maktab.domain.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends BaseEntityRepository<Transaction, Long> {
    List<Transaction> getTransactionsFromTo(String accountNumber, LocalDateTime fromDate, LocalDateTime toDate);
}
