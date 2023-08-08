package entity;
import java.util.Random;

/**
 * This class provides utility methods to generate random numbers and create unique IDs for different entities.
 */
public class RandomNumberGenerator {
    public static int generate(int digit){
        Random random = new Random();
        int min = (int) Math.pow(10, digit - 1);
        int max = (int) Math.pow(10, digit) - 1;

        return random.nextInt(max - min+1);
    }

    public static int addDigitToFront(int number, int digit){
        String numAsStr = String.valueOf(number);
        String digAsStr = String.valueOf(digit);
        String res = digAsStr + numAsStr;
        return Integer.parseInt(res);
    }

    public int generateAttorneyId(int digit){
        return addDigitToFront(generate(digit-1), 1);
    }
    public int generateClientId(int digit){
        return addDigitToFront(generate(digit-1), 2);
    }
    public int generateQuestionId(int digit){
        return addDigitToFront(generate(digit-1), 3);
    }
    public int generatePostId(int digit){
        return addDigitToFront(generate(digit-1), 4);
    }
}
