import driver.database.AttorneyRepository;
import entity.Attorney;
import AttorneyFactory

public class AttorneyRegister {
    public static void main(String[] args) {
        AttorneyRepository attorneyRepository = new AttorneyRepository();
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Set<String> professionals = new HashSet<>();
        professionals.add("Family and Children");
        professionals.add("Housing and Homelessness");
        professionals.add("Education");
        RegistrationData registrationData = new RegistrationData("Cathy", "zihan.yuan@mail.utoronto.ca", "12345678",
                "12345678", "CA", "12345", professionals);
        registrationData.setUserId(12345678);
        Attorney cathy = attorneyFactory.createUser(registrationData);
        attorneyRepository.save(cathy);

//        ------------------------------------------------
        AttorneyRepository attorneyRepository2 = new AttorneyRepository();
        AttorneyFactory attorneyFactory2 = new AttorneyFactory();
        Set<String> professionals2 = new HashSet<>();
        professionals2.add("Family and Children");
        RegistrationData registrationData2 = new RegistrationData("a2", "zihan.yuan@mail.utoronto.ca", "12345678",
                "12345678", "CA", "12345", professionals2);
        registrationData2.setUserId(12345678);
        Attorney a2 = attorneyFactory2.createUser(registrationData);
        attorneyRepository2.save(a2);


        AttorneyRepository attorneyRepository3 = new AttorneyRepository();
        AttorneyFactory attorneyFactory3 = new AttorneyFactory();
        Set<String> professionals3 = new HashSet<>();
        professionals3.add("Housing and Homelessness");
        RegistrationData registrationData3 = new RegistrationData("a3", "zihan.yuan@mail.utoronto.ca", "12345678",
                "12345678", "CA", "12345", professionals3);
        registrationData3.setUserId(12345678);
        Attorney a3 = attorneyFactory3.createUser(registrationData);
        attorneyRepository3.save(a3);

        AttorneyRepository attorneyRepository4 = new AttorneyRepository();
        AttorneyFactory attorneyFactory4 = new AttorneyFactory();
        Set<String> professionals4 = new HashSet<>();
        professionals4.add("Housing and Homelessness");
        RegistrationData registrationData4 = new RegistrationData("a4", "zihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals4);
        registrationData4.setUserId(12345678);
        Attorney a4 = attorneyFactory4.createUser(registrationData);
        attorneyRepository4.save(a4);

        AttorneyRepository attorneyRepository5 = new AttorneyRepository();
        AttorneyFactory attorneyFactory5 = new AttorneyFactory();
        Set<String> professionals5 = new HashSet<>();
        professionals5.add("Housing and Homelessness");
        RegistrationData registrationData5 = new RegistrationData("a5", "zihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals5);
        registrationData5.setUserId(12345678);
        Attorney a5 = attorneyFactory5.createUser(registrationData);
        attorneyRepository5.save(a5);


    }
}
