package usecases.requests;

import java.util.Set;

public class AttorneyRegistrationData extends RegistrationData {

    public AttorneyRegistrationData(Builder builder) {
        super(builder.userName, builder.email, builder.password, builder.password2, builder.stateAbb, builder.postalCode);
        this.professionals = builder.professionals;

    }

    public static class Builder {
        private final String userName;
        private final String email;
        private final String password;
        private final String stateAbb;
        private final String postalCode;
        private final String password2;
        private Set<String> professionals;

        public Builder(RegistrationData registrationData) {
            this.userName = registrationData.userName;
            this.email = registrationData.email;
            this.password = registrationData.password;
            this.password2 = registrationData.password2;
            this.stateAbb = registrationData.stateAbb;
            this.postalCode = registrationData.postalCode;
        }

        public AttorneyRegistrationData.Builder professionals(Set<String> professionals) {
            this.professionals = professionals;
            return this;
        }

        public AttorneyRegistrationData build() {return new AttorneyRegistrationData(this);}
    }
}
