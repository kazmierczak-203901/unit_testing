package pl.ttpsc.testing.junit.calculator;

public class SimpleCalculator {
    public double add(double number1, double number2) {

        return number1 + number2;
    }

    public double subtract(double number1, double number2) {
        return number1 - number2;
    }

    public double divide(double numerator, double denumetator) throws CannotDivideByZeroException {
        if (denumetator == 0.0d) {
            throw new CannotDivideByZeroException();
        }

        return numerator / denumetator;
    }

    public double calculateSquareRoot(double number) throws CannotCalculateSquareRootOfNegativeNumber {

        if (number < 0) {
            throw new CannotCalculateSquareRootOfNegativeNumber(number);
        }

        return Math.sqrt(number);

    }
}

class CannotDivideByZeroException extends Exception {
}

class CannotCalculateSquareRootOfNegativeNumber extends Exception {

    private double number;

    public CannotCalculateSquareRootOfNegativeNumber(double number) {
        this.number = number;
    }

    public double getNumberThatCausedException() {
        return number;
    }

}