package businessrule.requestmodel;

/**
 * This class represents a request model for client registration.
 * It contains various fields related to client registration information.
 */
public class ClientRegisterRequestModel {
    private final String userName;
    private final String email;
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

    /**
     * Constructs a new ClientRegisterRequestModel with the provided registration information.
     *
     * @param userName          The username for the client.
     * @param email             The email address of the client.
     * @param password          The password for the client's account.
     * @param password2         The confirmation password for the client's account.
     * @param stateAbb          The state abbreviation for the client's location.
     * @param postalCode        The postal code for the client's location.
     * @param ethnicity         The ethnicity of the client.
     * @param age               The age of the client.
     * @param gender            The gender of the client.
     * @param maritalStatus     The marital status of the client.
     * @param numberOfHousehold The number of people in the client's household.
     * @param annualIncome      The annual income of the client.
     */
    public ClientRegisterRequestModel(String userName, String email, String password,
                                      String password2, String stateAbb,
                                      String postalCode, String ethnicity,
                                      int age, String gender, String maritalStatus,
                                      int numberOfHousehold, float annualIncome) {
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

    /**
     * Gets the username of the client.
     *
     * @return The username of the client.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the email address of the client.
     *
     * @return The email address of the client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password of the client's account.
     *
     * @return The password of the client's account.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the confirmation password of the client's account.
     *
     * @return The confirmation password of the client's account.
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * Gets the state abbreviation of the client's location.
     *
     * @return The state abbreviation of the client's location.
     */
    public String getStateAbb() {
        return stateAbb;
    }

    /**
     * Gets the postal code of the client's location.
     *
     * @return The postal code of the client's location.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets the ethnicity of the client.
     *
     * @return The ethnicity of the client.
     */
    public String getEthnicity() {
        return ethnicity;
    }

    /**
     * Gets the age of the client.
     *
     * @return The age of the client.
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the gender of the client.
     *
     * @return The gender of the client.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the marital status of the client.
     *
     * @return The marital status of the client.
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Gets the number of people in the client's household.
     *
     * @return The number of people in the client's household.
     */
    public int getNumberOfHousehold() {
        return numberOfHousehold;
    }

    /**
     * Gets the annual income of the client.
     *
     * @return The annual income of the client.
     */
    public float getAnnualIncome() {
        return annualIncome;
    }
}
