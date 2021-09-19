package ir.maktab.service.front.menu;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstMenu extends Menu implements RunnableMenu<Void> {

    public FirstMenu() {
        super(new ArrayList<>(Arrays.asList("Log In", "Sign Up", "Exit")));
    }

    @Override
    public Void runMenu(){
//        while (true) {
//            switch (getItemFromConsole()) {
//                case 1:
//                    User loginUser = ApplicationContext.getUserService().login();
//                    if (!Objects.isNull(loginUser)) {
//                        new UserMenu(loginUser).runMenu();
//                    } else {
//                        System.out.println("Your password or username is wrong!");
//                    }
//                    break;
//                case 2:
//                    ApplicationContext.getUserService().signUp();
//                    break;
//                case 3:
//                    if (new CheckMenu("Are you sure you want to exit?").runMenu()) return null;
//                    else break;
//            }
//        }
        return null;
    }


}
