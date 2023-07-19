package clientregister;

public class ClientRegisterRequestModel {
    private final int userId;
    private final String password;
    private final String password2;
    private final String stateAbb;
    private final String postalCode;
    private final String ethnicity;
    private final int age;
    private final String gender;
    private final String maritalStatus;
    private final int numberOfHousehold;
    private final float annualIncome;

    public ClientRegisterRequestModel(int userId, String password,
                                      String password2, String stateAbb,
                                      String postalCode, String ethnicity,
                                      int age, String gender, String maritalStatus,
                                      int numberOfHousehold, float annualIncome) {
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public String getStateAbb() {
        return stateAbb;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public int getNumberOfHousehold() {
        return numberOfHousehold;
    }

    public float getAnnualIncome() {
        return annualIncome;
    }
}
