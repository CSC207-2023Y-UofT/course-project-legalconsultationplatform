package usecases.utils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a utility class for checking various credentials and data formats.
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

    public boolean checkStateAbb(String stateAbb){
        String pattern = "^(AL|AK|AZ|AR|CA|CO|CT|DE|FL|GA|HI|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VT|VA|WA|WV|WI|WY|Other)$";
        Pattern regexPattern = Pattern.compile(pattern);
        // Match the input against the pattern
        Matcher matcher = regexPattern.matcher(stateAbb);
        return matcher.matches();
    }
}
