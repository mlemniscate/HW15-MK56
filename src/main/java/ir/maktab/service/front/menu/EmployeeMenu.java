package ir.maktab.service.front.menu;

import ir.maktab.domain.BaseEmployee;

import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeMenu extends Menu implements RunnableMenu<Void>{
    public EmployeeMenu(BaseEmployee loginEmployee) {
        super(new ArrayList<>(Arrays.asList()));
    }

    @Override
    public Void runMenu() {
        return null;
    }
}
