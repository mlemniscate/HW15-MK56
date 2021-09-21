package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = Customer.TABLE_NAME)
@Inheritance
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity<Long> {

    public static final String TABLE_NAME = "customers";
    public static final String CUSTOMER_NUMBER = "customer_number";

    @Embedded
    private PersonInfo personInfo;

    @Column(name = CUSTOMER_NUMBER, unique = true, nullable = false)
    private Long customerNumber;

    @Override
    public String toString() {
        return "Customer{" +
                "personInfo=" + personInfo +
                ", customerNumber=" + customerNumber +
                '}';
    }
}
