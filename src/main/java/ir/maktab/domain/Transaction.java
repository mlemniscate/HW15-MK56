package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
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

    @Column(name = DATE)
    private LocalDateTime date;

}
