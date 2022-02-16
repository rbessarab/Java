package abc;

/**
 * @author Susan Uland, Ruslan Bessarab
 * @version 1.0
 * This class represents ALU for the ABC machine, and
 * is for arithmetic purposes, and stores the Nzp status
 */
public class ALU {

    private Nzp status; //holds Nzp.NEGATIVE, Nzp.ZERO, or Nzp.POSITIVE

    /**
     * Default constructor
     */
    public ALU() {
        status = Nzp.ZERO;
    }

    /**
     * This method will perform a math operation on two numbers and set the nzp status
     * appropriately based on whether the math operation resulting in a positive, negative, or zero value
     * @param num1
     * @param operator add, sub, mult or div
     * @param num2
     * @return answer for arithmetic between 2 numbers
     */
    public short operate(short num1, Operator operator, short num2) {
        short answer = 0;
        if(operator == Operator.ADD)
            answer = (short) (num1 + num2);

        if(operator == Operator.SUB)
            answer = (short) (num1 - num2);

        if(operator == Operator.MULT)
            answer = (short) (num1 * num2);

        if(operator == Operator.DIV)
            answer = (short) (num1 / num2);

        if(answer > 0)
            status = Nzp.POSITIVE;

        if(answer < 0)
            status = Nzp.NEGATIVE;

        if(answer == 0)
            status = Nzp.ZERO;

        return answer;//answer to ADD, SUB, MULT, DIV
    }

    /**
     * getter for status
     * @return status
     */
    public Nzp getStatus() {
        return status;
    }
}
