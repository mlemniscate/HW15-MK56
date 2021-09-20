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
public class BaseEmployee extends BaseEntity<Long> {

    public static final String TABLE_NAME = "employees";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMPLOYEE_ROLE = "employee_role";

    @Embedded
    private PersonInfo personInfo;

    @Column(name = USERNAME, nullable = false, unique = true)
    private String username;
    @Column(name = PASSWORD)
    private String password;
    @Column(name = EMPLOYEE_ROLE)
    @Enumerated(EnumType.STRING)
    private EmployeeRole employeeRole;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Builder(builderMethodName = "baseEmployeeBuilder")
    public BaseEmployee(PersonInfo personInfo, String username, String password, EmployeeRole employeeRole) {
        this.personInfo = personInfo;
        this.username = username;
        this.password = password;
        this.employeeRole = employeeRole;
    }
}
