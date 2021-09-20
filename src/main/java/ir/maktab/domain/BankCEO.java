package ir.maktab.domain;

import ir.maktab.domain.enums.EmployeeRole;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class BankCEO extends BaseEmployee{

    @Builder(builderMethodName = "ceoBuilder")
    public BankCEO(PersonInfo personInfo, String username, String password, EmployeeRole employeeRole) {
        super(personInfo, username, password, employeeRole);
    }
}
