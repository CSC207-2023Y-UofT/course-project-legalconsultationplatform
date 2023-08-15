package usecases.requests;

public class ClientRegistrationData extends RegistrationData {
    public ClientRegistrationData(Builder builder) {
        super(builder.userName, builder.email, builder.password, builder.password2, builder.stateAbb, builder.postalCode);
        this.ethnicity = builder.ethnicity;
        this.age = builder.age;
        this.gender = builder.gender;
        this.maritalStatus = builder.maritalStatus;
        this.numberOfHousehold = builder.numberOfHousehold;
        this.annualIncome = builder.annualIncome;
    }

    public static class Builder {
        private final String userName;
        private final String email;
        private final String password;
        private final String stateAbb;
        private final String postalCode;
        private String ethnicity;
        private int age;
        private String gender;
        private String maritalStatus;
        private int numberOfHousehold;
        private float annualIncome;
        private final String password2;

        public Builder(RegistrationData registrationData) {
            this.userName = registrationData.userName;
            this.email = registrationData.email;
            this.password = registrationData.password;
            this.password2 = registrationData.password2;
            this.stateAbb = registrationData.stateAbb;
            this.postalCode = registrationData.postalCode;
        }

        public ClientRegistrationData.Builder ethnicity(String ethnicity) {
            this.ethnicity = ethnicity;
            return this;
        }

        public ClientRegistrationData.Builder age(int age) {
            this.age = age;
            return this;
        }

        public ClientRegistrationData.Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public ClientRegistrationData.Builder maritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public ClientRegistrationData.Builder numberOfHousehold(int numberOfHousehold) {
            this.numberOfHousehold = numberOfHousehold;
            return this;
        }

        public ClientRegistrationData.Builder annualIncome(float annualIncome) {
            this.annualIncome = annualIncome;
            return this;
        }

        public ClientRegistrationData build() {return new ClientRegistrationData(this);}
    }
}
