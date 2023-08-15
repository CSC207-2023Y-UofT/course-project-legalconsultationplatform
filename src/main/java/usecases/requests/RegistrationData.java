package usecases.requests;

import java.util.Set;

/**
 * This class represents registration data for a user, client, or attorney.
 */
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

    public RegistrationData() {
    }

    public RegistrationData(String userName, String email, String password, String password2, String stateAbb, String postalCode) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.password2 = password2;
        this.stateAbb = stateAbb;
        this.postalCode = postalCode;
    }

    public RegistrationData(Builder builder) {
        this.userName = builder.userName;
        this.email = builder.email;
        this.password = builder.password;
        this.password2 = builder.password2;
        this.stateAbb = builder.stateAbb;
        this.postalCode = builder.postalCode;
    }

    public void setUserId(int userId) {this.userId = userId;}

    public static class Builder {
        private String userName;
        private String email;
        private String password;
        private String stateAbb;
        private String postalCode;
        private String password2;

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder stateAbb(String stateAbb) {
            this.stateAbb = stateAbb;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder password2(String password) {
            this.password2 = password;
            return this;
        }

        public RegistrationData build() {return new RegistrationData(this);}
    }
}
