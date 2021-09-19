package ir.maktab.domain;

import ir.maktab.base.domain.BaseEntity;
import ir.maktab.domain.enums.EmployeeRole;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = BaseEmployee.TABLE_NAME)
@Inheritance
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseEmployee extends BaseEntity<Long> {

    public static final String TABLE_NAME = "employees";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMPLOYEE_ROLE = "employee_role";

    @Embedded
    private PersonInfo personInfo;

    @Column(name = USERNAME)
    private String username;
    @Column(name = PASSWORD)
    private String password;
    @Column(name = EMPLOYEE_ROLE)
    @Enumerated(EnumType.STRING)
    private EmployeeRole employeeRole;

}
