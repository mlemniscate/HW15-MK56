package ir.maktab.service.front.menu;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckMenu extends Menu implements RunnableMenu<Boolean>{
    private final String message;
    private boolean isAccepted;

    public CheckMenu(String message) {
        super(new ArrayList<>(Arrays.asList("Yes", "No")));
        this.message = message;
    }

    @Override
    public Boolean runMenu() {
        System.out.println("\n" + message);
        while(true) {
            switch (getItemFromConsole()) {
                case 1:
                    return true;
                case 2:
                    return false;
            }
        }
    }
}
