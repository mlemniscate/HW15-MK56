package ir.maktab.service.front.menu;


import ir.maktab.service.front.input.InputInt;

import java.util.List;

public class Menu {
    private List<String> items;

    public Menu(List<String> items) {
        this.items = items;
    }

    private void showMenu() {
        System.out.println("------------------------------------------");
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("[%d] - %s%n", (i+1), items.get(i));
        }
        System.out.println("------------------------------------------");
    }

    public int getItemFromConsole() {
        showMenu();
        return new InputInt("Enter an item number: ", items.size(), 1, null)
                .getIntInput();
    }

    public List<String> getItems() {
        return items;
    }
}
