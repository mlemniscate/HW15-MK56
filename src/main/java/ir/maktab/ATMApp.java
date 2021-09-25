package ir.maktab;

import ir.maktab.domain.Account;
import ir.maktab.service.front.input.InputString;
import ir.maktab.service.front.menu.CheckMenu;
import ir.maktab.service.front.menu.Menu;
import ir.maktab.util.ApplicationContext;
import ir.maktab.util.HibernateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ATMApp {

    private static Account account;

    public static void main(String[] args) {
        HibernateUtil.getMainEntityManagerFactory().createEntityManager();
        Menu menu = new Menu(new ArrayList<>(Arrays.asList("Enter to ATM", "Exit")));
        while (true) {
            switch (menu.getItemFromConsole()) {
                case 1:
                    String cartNumber = enterCartNumber();
                    if(isValidCard(cartNumber)) {
                        atmMenu();
                    }
                    break;
                case 2:
                    if (new CheckMenu("Are you sure you want to exit?").runMenu()) return;
                    else break;
            }
        }

    }

    private static boolean isValidCard(String cartNumber) {
        if(ApplicationContext.getCreditCardService().existsByCardNumber(cartNumber)) {
            account = ApplicationContext.getAccountService().getByCardNumber(cartNumber);
            for (int i = 0; i < 3; i++) {
                String password = enterPassword();
                if(account.getCreditCart().getPassword().equals(password)) {
                    if(!account.isDisabled()) return true;
                    else {
                        System.out.println("Your account is disabled!");
                        return false;
                    }
                } else {
                    System.out.println("Your password is wrong!");
                }
            }
            account.setDisabled(true);
            ApplicationContext.getAccountService().save(account);
        } else {
            System.out.println("You card number is wrong!");
        }
        return false;
    }

    public static void atmMenu() {
        Menu menu = new Menu(new ArrayList<>(Arrays.asList(
                "Card to Card Payment",
                "Set Or Edit Cart Second Password",
                "Set Or Edit Cart First Password",
                "Exit")));
        while (true) {
            switch (menu.getItemFromConsole()) {
                case 1:
                    cardToCardPayment();
                    break;
                case 2:
                    String secondPassword = enterSecondPassword();
                    account.getCreditCart().setSecondPassword(secondPassword);
                    ApplicationContext.getAccountService().save(account);
                    break;
                case 3:
                    String password = enterPassword();
                    account.getCreditCart().setPassword(password);
                    ApplicationContext.getAccountService().save(account);
                    break;
                case 4:
                    if (new CheckMenu("Are you sure you want to exit?").runMenu()) return;
                    else break;
            }
        }
    }

    private static void cardToCardPayment() {
        String destinationCardNum = enterCartNumber();
        Integer transferMoney = enterTransferMoney();
        if (checkCardsAndTransferMoneyInfo(destinationCardNum, transferMoney)) {
            Integer cvv2 = enterCVV2();
            LocalDate expireDate = enterExpireDate();
            String secondPassword = enterSecondPassword();
            if(checkCardTransferInfo(cvv2, expireDate, secondPassword, transferMoney)) {
                doCardToCardMoneyTransfer(destinationCardNum, transferMoney);
            } else {
                System.out.println("Your information is wrong!");
            }
        }
    }

    private static void doCardToCardMoneyTransfer(String destinationCardNum, Integer transferMoney) {
        Account destinationAccount = ApplicationContext.getAccountService().getByCardNumber(destinationCardNum);
        account.setBalance(account.getBalance() - transferMoney - 600);
        destinationAccount.setBalance(destinationAccount.getBalance() + transferMoney);
        ApplicationContext.getAccountService().save(account);
        ApplicationContext.getAccountService().save(destinationAccount);
    }

    private static boolean checkCardTransferInfo(Integer cvv2, LocalDate expireDate,
                                                 String secondPassword, Integer transferMoney) {
        String msg = "";
        msg += account.getCreditCart().getCvv2().intValue() == cvv2.intValue() ? "" : "Your cvv2 is wrong!\n";
        msg += account.getCreditCart().getSecondPassword().equals(secondPassword) ? "" : "Your second password is wrong!\n";
        msg += account.getCreditCart().getExpirationDate().equals(expireDate) ? "" : "Your expiration date is wrong!\n";
        System.out.println(msg);
        return  msg.equals("");
    }

    private static String enterSecondPassword() {
        return new InputString("Enter your second password: ", "^\\d{5,8}$")
                .getStringInput();
    }

    private static String enterPassword() {
        return new InputString("Enter your password: ","Your password must be 4 digit", "^\\d{4}$", null)
                .getStringInput();
    }

        private static LocalDate enterExpireDate() {
        String stringInput = new InputString("Enter your credit card expire date: ", "^\\d{4}-\\d{1,2}-\\d{1,2}$")
                .getStringInput();
        String[] splitDate = stringInput.split("-");
        return LocalDate.of(Integer.parseInt(splitDate[0]),
                Integer.parseInt(splitDate[1]),
                Integer.parseInt(splitDate[2]));
    }

    private static Integer enterCVV2() {
        return Integer.parseInt(new InputString("Enter your cvv2: ").getStringInput());
    }

    private static boolean checkCardsAndTransferMoneyInfo(String destinationCartNum, Integer transferMoney) {
        String msg = "";
        msg += ApplicationContext.getCreditCardService().existsByCardNumber(destinationCartNum) ? "" : "Your destination card number is wrong!\n";
        msg += transferMoney < 3_000_000 ? "" : "Your transfer money is greater then 3,000,000\n";
        msg += account.getBalance() - 5600 >= transferMoney ? "" : "Your transfer money is not enough!\n";
        System.out.println(msg);
        return msg.equals("");
    }

    private static Integer enterTransferMoney() {
        return Integer.parseInt(new InputString("Enter your money want to transfer: ")
                .getStringInput());
    }

    private static String enterCartNumber() {
        return new InputString("Enter card number: ", "(^\\d{12}$)|(^\\d{16}$)")
                .getStringInput();
    }
}
