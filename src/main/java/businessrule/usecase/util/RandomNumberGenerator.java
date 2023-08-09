package businessrule.usecase.util;

import java.util.Random;

/**
 * This class provides utility methods to generate random numbers and create unique IDs for different entities.
 */
public class RandomNumberGenerator {

    /**
     * Generates a random integer with the specified number of digits.
     *
     * @param digit The number of digits the generated random number should have.
     * @return A random integer with the specified number of digits.
     */
    public static int generate(int digit) {
        Random random = new Random();
        int min = (int) Math.pow(10, digit - 1);
        int max = (int) Math.pow(10, digit) - 1;

        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Adds a digit to the front of an existing number.
     *
     * @param number The existing number.
     * @param digit The digit to be added to the front.
     * @return The resulting number after adding the digit to the front.
     */
    public static int addDigitToFront(int number, int digit) {
        String numAsStr = String.valueOf(number);
        String digAsStr = String.valueOf(digit);
        String res = digAsStr + numAsStr;
        return Integer.parseInt(res);
    }

    /**
     * Generates a unique attorney ID with the specified number of digits.
     *
     * @param digit The number of digits the generated attorney ID should have.
     * @return A unique attorney ID with the specified number of digits.
     */
    public int generateAttorneyId(int digit) {
        return addDigitToFront(generate(digit - 1), 1);
    }

    /**
     * Generates a unique client ID with the specified number of digits.
     *
     * @param digit The number of digits the generated client ID should have.
     * @return A unique client ID with the specified number of digits.
     */
    public int generateClientId(int digit) {
        return addDigitToFront(generate(digit - 1), 2);
    }

    /**
     * Generates a unique question ID with the specified number of digits.
     *
     * @param digit The number of digits the generated question ID should have.
     * @return A unique question ID with the specified number of digits.
     */
    public int generateQuestionId(int digit) {
        return addDigitToFront(generate(digit - 1), 3);
    }

    /**
     * Generates a unique post ID with the specified number of digits.
     *
     * @param digit The number of digits the generated post ID should have.
     * @return A unique post ID with the specified number of digits.
     */
    public int generatePostId(int digit) {
        return addDigitToFront(generate(digit - 1), 4);
    }
}