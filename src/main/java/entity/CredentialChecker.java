package entity;
import org.apache.commons.validator.routines.EmailValidator;
import java.util.regex.*;

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
