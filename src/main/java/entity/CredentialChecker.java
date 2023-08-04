package entity;
import org.apache.commons.validator.routines.EmailValidator;
import java.util.regex.*;

/**
 * This is a utility class for checking various credentials and data formats.
 *
 * The "CredentialChecker" class provides methods to validate different types of credentials,
 * ensuring that they meet specific criteria or follow certain patterns.
*/


public class CredentialChecker {

    public boolean checkPostalCode(String postalCode){
        String pattern = "^\\d{5}$";
        return postalCode.matches(pattern);
    }

    public boolean checkEmail(String email){
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public boolean checkAge(int age){
        return age >= 1 && age <= 99;
    }
}
