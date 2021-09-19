package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Branch.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Branch extends BaseEntity<Long> {
    public static final String TABLE_NAME = "branches";
    public static final String NAME = "name";
    public static final String CODE = "code";
    public static final String CITY = "city";
    public static final String ADDRESS = "address";

    @Column(name = NAME)
    private String name;
    @Column(name = CODE)
    private String code;
    @Column(name = CITY)
    private String city;
    @Column(name = ADDRESS)
    private String address;
}
