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
    private Double moneyAmount;
    @Column(name = TRANSFER_ACCOUNT_ID)
    private Long transferAccountId;

}
