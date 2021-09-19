package ir.maktab.service.front.input;

import java.util.InputMismatchException;

/*
 * InputInt for taking int inputs from console.
 * */
public class InputInt extends Input {

    private final int maxValue;
    private final int minValue;
    private final int[] unAllowedIntValues;
    private int intInput;

    public InputInt(String inputMessage, String warningMessage, int maxValue, int minValue,
                    int[] unAllowedIntValues) {
        super(inputMessage, warningMessage);
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.unAllowedIntValues = unAllowedIntValues;
    }

    public InputInt(String inputMessage, int maxValue, int minValue,
                    int[] unAllowedIntValues) {
        this(inputMessage, "Input value is not valid.\nPlease try again!", maxValue, minValue, unAllowedIntValues);
    }

    private void setIntInput() throws InputMismatchException {
        System.out.print(getInputMessage());
        this.intInput = scanner.nextInt();
    }

    // This method validate int input
    @Override
    public boolean validations() {
        return this.intInput >= minValue &&
                this.intInput <= maxValue &&
                !equalUnAllowedValues();
    }

    // Check that input equals to any of the unAllowedValues elements.
    private boolean equalUnAllowedValues() {
        if (unAllowedIntValues != null)
            for (int unAllowedValue : unAllowedIntValues) if (intInput == unAllowedValue) return true;
        return false;
    }

    public int getIntInput() {
        boolean continueLoop = true;
        do {
            try {
                setIntInput();
                continueLoop = false;
                if (!validations()) {
                    System.out.println(getWarningMessage());
                    continueLoop = true;
                } else return intInput;
            } catch (InputMismatchException inputMismatchException) {
                scanner.next();
                System.out.println(getWarningMessage());
            }
        } while (continueLoop);
        return 0;
    }
}
