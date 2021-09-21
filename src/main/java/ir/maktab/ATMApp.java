package ir.maktab;

import ir.maktab.domain.Account;
import ir.maktab.domain.CreditCard;
import ir.maktab.service.front.input.InputString;
import ir.maktab.service.front.menu.CheckMenu;
import ir.maktab.service.front.menu.Menu;
import ir.maktab.util.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ATMApp {
    public static void main(String[] args) {
        atmMenu();
    }

    public static void atmMenu() {
        Menu menu = new Menu(new ArrayList<>(Arrays.asList(
                "Cart to Cart Payment",
                "Set Or Edit Cart Second Password",
                "Set Or Edit Cart First Password",
                "Exit")));
        while (true) {
            switch (menu.getItemFromConsole()) {
                case 1:
                    String sourceCardNum = enterCartNumber();
                    String destinationCardNum = enterCartNumber();
                    Integer transferMoney = enterTransferMoney();
                    if (checkFirstInformation(sourceCardNum, destinationCardNum, transferMoney)) {
                        CreditCard sourceCreditCard = ApplicationContext.getCreditCardService().getByCardNumber(sourceCardNum);
                        Account sourceAccount = ApplicationContext.getAccountService().getByCardId(sourceCreditCard.getId());
                        Integer cvv2 = enterCVV2();
                        LocalDate expireDate = enterExpireDate();
                        String secondPassword = enterSecondPassword();
                        if(checkCardInformation(sourceCreditCard, cvv2, expireDate, secondPassword, sourceAccount, transferMoney)) {
                            CreditCard destinationCreditCard = ApplicationContext.getCreditCardService().getByCardNumber(destinationCardNum);
                            Account destinationAccount = ApplicationContext.getAccountService().getByCardId(destinationCreditCard.getId());
                            sourceAccount.setBalance(sourceAccount.getBalance() - transferMoney);
                            destinationAccount.setBalance(destinationAccount.getBalance() + transferMoney);
                            ApplicationContext.getAccountService().save(sourceAccount);
                            ApplicationContext.getAccountService().save(destinationAccount);
                        } else {
                            System.out.println("Your information is wrong!");
                        }
                    } else {
                        System.out.println("Your information is wrong!");
                    }
                    break;
                case 2:
                    String cartNumber = enterCartNumber();
                    if(ApplicationContext.getCreditCardService().existsByCardNumber(cartNumber)) {
                        CreditCard card = ApplicationContext.getCreditCardService().getByCardNumber(cartNumber);
                    } else System.out.println("Your cardNumber is wrong!");
                case 4:
                    if (new CheckMenu("Are you sure you want to exit?").runMenu()) return;
                    else break;
            }
        }
    }

    private static boolean checkCardInformation(CreditCard sourceCreditCard, Integer cvv2,
                                                LocalDate expireDate, String secondPassword,
                                                Account sourceAccount, Integer transferMoney) {
        return sourceCreditCard.getCvv2().equals(cvv2) &&
                sourceCreditCard.getSecondPassword().equals(secondPassword) &&
                sourceCreditCard.getExpirationDate().equals(expireDate) &&
                sourceAccount.getBalance() - 5000 > transferMoney &&
                !sourceAccount.isDisabled();
    }

    private static String enterSecondPassword() {
        return new InputString("Enter your second password: ", "^\\d{5,8}$")
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

    private static boolean checkFirstInformation(String sourceCartNum, String destinationCartNum, Integer transferMoney) {
        return ApplicationContext.getCreditCardService().existsByCardNumber(sourceCartNum) &&
                ApplicationContext.getCreditCardService().existsByCardNumber(destinationCartNum) &&
                transferMoney < 3_000_000;
    }

    private static Integer enterTransferMoney() {
        return Integer.parseInt(new InputString("Enter your money want to transfer: ")
                .getStringInput());
    }

    private static String enterCartNumber() {
        return new InputString("Enter your source card number: ", "(^\\d{12}$)|(^\\d{16}$)")
                .getStringInput();
    }
}
