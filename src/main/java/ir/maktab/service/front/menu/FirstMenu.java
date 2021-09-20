package ir.maktab.service.front.menu;

import ir.maktab.domain.BaseEmployee;
import ir.maktab.domain.Employee;
import ir.maktab.service.front.input.InputString;
import ir.maktab.util.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FirstMenu extends Menu implements RunnableMenu<Void> {

    public FirstMenu() {
        super(new ArrayList<>(Arrays.asList("Log In", "Exit")));
    }

    @Override
    public Void runMenu(){
        while (true) {
            switch (getItemFromConsole()) {
                case 1:
                    String username = getUsername(), password = getPassword();
                    BaseEmployee loginEmployee = ApplicationContext.getBaseEmployeeService().login(username, password);
                    if (!Objects.isNull(loginEmployee) && loginEmployee instanceof Employee) {
                        new EmployeeMenu(loginEmployee).runMenu();
                    } else {
                        System.out.println("Your username or password is wrong!");
                    }
                    break;
                case 2:
                    if (new CheckMenu("Are you sure you want to exit?").runMenu()) return null;
                    else break;
            }
        }
    }

    private String getPassword() {
        return new InputString("Enter your password: ").getStringInput();
    }

    private String getUsername() {
        return new InputString("Enter your username: ").getStringInput();
    }

}
