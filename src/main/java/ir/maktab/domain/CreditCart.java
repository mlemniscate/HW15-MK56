package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = CreditCart.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCart extends BaseEntity<Long> {

    public static final String TABLE_NAME = "credit_cats";
    public static final String NUMBER = "number";
    public static final String PASSWORD = "password";
    public static final String SECOND_PASSWORD = "second_password";
    public static final String CVV2 = "cvv2";
    public static final String EXPIRATION_DATE = "expiration_date";

    @Column(name = NUMBER)
    private String number;
    @Column(name = PASSWORD)
    private String password;
    @Column(name = SECOND_PASSWORD)
    private String secondPassword;
    @Column(name = CVV2)
    private Integer cvv2;
    @Column(name = EXPIRATION_DATE)
    private LocalDate expirationDate;
}
