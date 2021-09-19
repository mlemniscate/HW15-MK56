package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import ir.maktab.domain.enums.AccountType;
import lombok.*;

import javax.persistence.*;

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
    @Column(name = IS_DISABLED)
    private Boolean isDisabled;
}
