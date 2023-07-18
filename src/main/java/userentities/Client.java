package userentities;

public class Client implements User{
    private final int userID;
    private final String password;
    private final String StateAbb;
    private final String PostalCode;
    private final String ethnicity;
    private final int age;
    private final String gender;
    private final String MaritalStatus;
    private final int NumberOfHousehold;
    private final float AnnualIncome;

    Client(int userID, String password, String StateAbb, String PostalCode, String ethnicity, int age,
           String gender, String MaritalStatus, int NumberOfHousehold, float AnnualIncome){
        this.userID = userID;
        this.password = password;
        this.StateAbb = StateAbb;
        this.PostalCode = PostalCode;
        this.ethnicity = ethnicity;
        this.age = age;
        this.gender = gender;
        this.MaritalStatus = MaritalStatus;
        this.NumberOfHousehold = NumberOfHousehold;
        this.AnnualIncome = AnnualIncome;
    }

    @Override
    public int getUserID() {
        return userID;
    }
    @Override
    public String getPassword() {
        return password;
    }

    public String getStateAbb() {
        return StateAbb;
    }

    public String getPostalCode() {
        return PostalCode;
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
        return MaritalStatus;
    }

    public int getNumberOfHousehold() {
        return NumberOfHousehold;
    }

    public float getAnnualIncome() {
        return AnnualIncome;
    }
}
