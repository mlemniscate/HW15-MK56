package ir.maktab.domain;

import ir.maktab.domain.enums.EmployeeRole;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Employee extends BaseEmployee{

    @Builder(builderMethodName = "employeeBuilder")
    public Employee(PersonInfo personInfo, String username, String password, EmployeeRole employeeRole) {
        super(personInfo, username, password, employeeRole);
    }
}
