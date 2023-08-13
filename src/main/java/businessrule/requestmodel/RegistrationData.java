package businessrule.requestmodel;

import java.util.Set;

public class RegistrationData {
    // common fields for user registration
    public int userId;
    public String userName;
    public String email;
    public String password;
    public String stateAbb;
    public String postalCode;

    // fields for client registration
    public String ethnicity;
    public int age;
    public String gender;
    public String maritalStatus;
    public int numberOfHousehold;
    public float annualIncome;
    public String password2;

    // fields for attorney registration
    public Set<String> professionals;

    // for user
    public RegistrationData(String userName, String email, String password, String password2, String stateAbb, String postalCode) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.password2 = password2;
        this.stateAbb = stateAbb;
        this.postalCode = postalCode;
    }

    // for client registration
    public RegistrationData(String userName, String email, String password, String password2, String stateAbb, String postalCode, String ethnicity, int age, String gender, String maritalStatus, int numberOfHousehold, float annualIncome) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.password2 = password2;
        this.stateAbb = stateAbb;
        this.postalCode = postalCode;
        this.ethnicity = ethnicity;
        this.age = age;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.numberOfHousehold = numberOfHousehold;
        this.annualIncome = annualIncome;
    }

    public RegistrationData(String userName, String email, String password, String password2, String stateAbb, String postalCode, Set<String> professionals) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.password2 = password2;
        this.stateAbb = stateAbb;
        this.postalCode = postalCode;
        this.professionals = professionals;
    }


    public void setUserId(int userId) {this.userId = userId;}
}
