package ir.maktab.service.front.input;

import java.util.InputMismatchException;

/*
 * InputDouble for taking double inputs from console.
 * */
public class InputDouble extends Input {

    private final double maxValue;
    private final double minValue;
    private final double[] unAllowedIntValues;
    private double doubleInput;

    public InputDouble(String inputMessage, String warningMessage, double maxValue, double minValue,
                       double[] unAllowedIntValues) {
        super(inputMessage, warningMessage);
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.unAllowedIntValues = unAllowedIntValues;
    }

    public InputDouble(String inputMessage, double maxValue, double minValue,
                       double[] unAllowedIntValues) {
        this(inputMessage, "Input value is not valid.\nPlease try again!", maxValue, minValue, unAllowedIntValues);
    }

    private void setDoubleInput() {
        System.out.print(getInputMessage());
        this.doubleInput = scanner.nextInt();
    }

    // This method validate int input
    @Override
    public boolean validations() {
        return this.doubleInput >= minValue &&
                this.doubleInput <= maxValue &&
                !equalUnAllowedValues();
    }

    // Check that input equals to any of the unAllowedValues elements.
    private boolean equalUnAllowedValues() {
        if (unAllowedIntValues != null)
            for (double unAllowedValue : unAllowedIntValues) if (doubleInput == unAllowedValue) return true;
        return false;
    }

    public double getDoubleInput() {
        boolean continueLoop = true;
        do {
            try {
                setDoubleInput();
                continueLoop = false;
                if (!validations()) {
                    System.out.println(getWarningMessage());
                    continueLoop = true;
                } else return doubleInput;
            } catch (InputMismatchException inputMismatchException) {
                scanner.next();
                System.out.println(getWarningMessage());
            }
        } while (continueLoop);
        return 0;
    }
}
