package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import ir.maktab.domain.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = Transaction.TABLE_NAME)
@Inheritance
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction extends BaseEntity<Long> {

    public static final String TABLE_NAME = "transactions";
    public static final String DATE = "date";
    public static final String TRANSACTION_TYPE = "transaction_type";
    public static final String MONEY_AMOUNT = "money_amount";
    public static final String TRANSFER_ACCOUNT_ID = "transfer_account_id";

    @Column(name = DATE)
    private LocalDateTime date;
    @Column(name = TRANSACTION_TYPE)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Column(name = MONEY_AMOUNT)
    private Integer moneyAmount;
    @Column(name = TRANSFER_ACCOUNT_ID)
    private Long transferAccountId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", transactionType=" + transactionType +
                ", moneyAmount=" + moneyAmount +
                ", transferAccountId=" + transferAccountId +
                '}';
    }
}
