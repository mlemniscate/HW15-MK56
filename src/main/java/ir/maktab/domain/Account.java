package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import ir.maktab.domain.enums.AccountType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Account.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends BaseEntity<Long> {

    public static final String TABLE_NAME = "accounts";
    public static final String ACCOUNT_NUMBER = "account_number";
    public static final String ACCOUNT_TYPE = "account_type";
    public static final String BALANCE = "balance";
    public static final String IS_DISABLED = "is_disabled";

    @Column(name = ACCOUNT_NUMBER)
    private String accountNumber;

    @Column(name = ACCOUNT_TYPE)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = BALANCE)
    private Integer balance;

    @Column(name = IS_DISABLED, columnDefinition="tinyint(1) default 1")
    private boolean isDisabled = false;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCart;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "account_id")
    private List<Transaction> transactionList = new ArrayList<>();

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", isDisabled=" + isDisabled +
                ", customer=" + customer +
                ", creditCart=" + creditCart +
                '}';
    }
}
