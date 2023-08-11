import businessrule.requestmodel.RegistrationData;
import driver.database.AttorneyRepository;
import entity.Attorney;
import entity.factory.AttorneyFactory;

import java.util.HashSet;
import java.util.Set;


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
        RegistrationData registrationData2 = new RegistrationData("a2", "ihan.yuan@mail.utoronto.ca", "12345678",
                "12345678", "CA", "12345", professionals2);
        registrationData2.setUserId(123456781);
        Attorney a2 = attorneyFactory2.createUser(registrationData2);
        attorneyRepository2.save(a2);


        AttorneyRepository attorneyRepository3 = new AttorneyRepository();
        AttorneyFactory attorneyFactory3 = new AttorneyFactory();
        Set<String> professionals3 = new HashSet<>();
        professionals3.add("Consumer Financial Questions");
        RegistrationData registrationData3 = new RegistrationData("a3", "ihan.yuan@mail.utoronto.ca", "12345678",
                "12345678", "CA", "12345", professionals3);
        registrationData3.setUserId(123456782);
        Attorney a3 = attorneyFactory3.createUser(registrationData3);
        attorneyRepository3.save(a3);

        AttorneyRepository attorneyRepository4 = new AttorneyRepository();
        AttorneyFactory attorneyFactory4 = new AttorneyFactory();
        Set<String> professionals4 = new HashSet<>();
        professionals4.add("Housing and Homelessness");
        RegistrationData registrationData4 = new RegistrationData("a4", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals4);
        registrationData4.setUserId(123456783);
        Attorney a4 = attorneyFactory4.createUser(registrationData4);
        attorneyRepository4.save(a4);

        AttorneyRepository attorneyRepository5 = new AttorneyRepository();
        AttorneyFactory attorneyFactory5 = new AttorneyFactory();
        Set<String> professionals5 = new HashSet<>();
        professionals5.add(" Individual Rights");
        RegistrationData registrationData5 = new RegistrationData("a5", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals5);
        registrationData5.setUserId(123456784);
        Attorney a5 = attorneyFactory5.createUser(registrationData5);
        attorneyRepository5.save(a5);

        AttorneyRepository attorneyRepository6 = new AttorneyRepository();
        AttorneyFactory attorneyFactory6 = new AttorneyFactory();
        Set<String> professionals6 = new HashSet<>();
        professionals6.add("Income Maintenance");
        RegistrationData registrationData6 = new RegistrationData("a6", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals6);
        registrationData6.setUserId(123456785);
        Attorney a6 = attorneyFactory6.createUser(registrationData6);
        attorneyRepository6.save(a6);

        AttorneyRepository attorneyRepository7 = new AttorneyRepository();
        AttorneyFactory attorneyFactory7 = new AttorneyFactory();
        Set<String> professionals7 = new HashSet<>();
        professionals7.add("Health and Disability");
        RegistrationData registrationData7 = new RegistrationData("a7", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals7);
        registrationData7.setUserId(123456786);
        Attorney a7 = attorneyFactory7.createUser(registrationData7);
        attorneyRepository7.save(a7);

        AttorneyRepository attorneyRepository8 = new AttorneyRepository();
        AttorneyFactory attorneyFactory8 = new AttorneyFactory();
        Set<String> professionals8 = new HashSet<>();
        professionals8.add("Work, Employment and Unemployment");
        RegistrationData registrationData8 = new RegistrationData("a8", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals8);
        registrationData8.setUserId(123456787);
        Attorney a8 = attorneyFactory8.createUser(registrationData8);
        attorneyRepository8.save(a8);

        AttorneyRepository attorneyRepository9 = new AttorneyRepository();
        AttorneyFactory attorneyFactory9 = new AttorneyFactory();
        Set<String> professionals9 = new HashSet<>();
        professionals9.add("Juvenile");
        RegistrationData registrationData9 = new RegistrationData("a9", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals9);
        registrationData9.setUserId(123456788);
        Attorney a9 = attorneyFactory9.createUser(registrationData9);
        attorneyRepository9.save(a9);

        AttorneyRepository attorneyRepository10 = new AttorneyRepository();
        AttorneyFactory attorneyFactory10 = new AttorneyFactory();
        Set<String> professionals10 = new HashSet<>();
        professionals10.add("Education");
        RegistrationData registrationData10 = new RegistrationData("a10", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals10);
        registrationData10.setUserId(123456789);
        Attorney a10 = attorneyFactory10.createUser(registrationData10);
        attorneyRepository10.save(a10);

        AttorneyRepository attorneyRepository21 = new AttorneyRepository();
        AttorneyFactory attorneyFactory21 = new AttorneyFactory();
        Set<String> professionals21 = new HashSet<>();
        professionals21.add("Family and Children");
        RegistrationData registrationData21 = new RegistrationData("a21", "zhan.yuan@mail.utoronto.ca", "12345678",
                "12345678", "CA", "12345", professionals21);
        registrationData21.setUserId(1234567810);
        Attorney a21 = attorneyFactory21.createUser(registrationData21);
        attorneyRepository21.save(a21);


        AttorneyRepository attorneyRepository31 = new AttorneyRepository();
        AttorneyFactory attorneyFactory31 = new AttorneyFactory();
        Set<String> professionals31 = new HashSet<>();
        professionals31.add("Consumer Financial Questions");
        RegistrationData registrationData31 = new RegistrationData("a31", "zhan.yuan@mail.utoronto.ca", "12345678",
                "12345678", "CA", "12345", professionals31);
        registrationData31.setUserId(1234567811);
        Attorney a31 = attorneyFactory31.createUser(registrationData31);
        attorneyRepository31.save(a31);

        AttorneyRepository attorneyRepository41 = new AttorneyRepository();
        AttorneyFactory attorneyFactory41 = new AttorneyFactory();
        Set<String> professionals41 = new HashSet<>();
        professionals41.add("Housing and Homelessness");
        RegistrationData registrationData41 = new RegistrationData("a41", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals41);
        registrationData41.setUserId(1234567812);
        Attorney a41 = attorneyFactory41.createUser(registrationData41);
        attorneyRepository41.save(a41);

        AttorneyRepository attorneyRepository51 = new AttorneyRepository();
        AttorneyFactory attorneyFactory51 = new AttorneyFactory();
        Set<String> professionals51 = new HashSet<>();
        professionals51.add(" Individual Rights");
        RegistrationData registrationData51 = new RegistrationData("a51", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals51);
        registrationData51.setUserId(1234567813);
        Attorney a51 = attorneyFactory51.createUser(registrationData51);
        attorneyRepository5.save(a51);

        AttorneyRepository attorneyRepository61 = new AttorneyRepository();
        AttorneyFactory attorneyFactory61 = new AttorneyFactory();
        Set<String> professionals61 = new HashSet<>();
        professionals61.add("Income Maintenance");
        RegistrationData registrationData61 = new RegistrationData("a61", "zhan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals61);
        registrationData61.setUserId(1234567814);
        Attorney a61 = attorneyFactory61.createUser(registrationData61);
        attorneyRepository61.save(a61);

        AttorneyRepository attorneyRepository71 = new AttorneyRepository();
        AttorneyFactory attorneyFactory71 = new AttorneyFactory();
        Set<String> professionals71 = new HashSet<>();
        professionals71.add("Health and Disability");
        RegistrationData registrationData71 = new RegistrationData("a71", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals71);
        registrationData71.setUserId(1234567815);
        Attorney a71 = attorneyFactory7.createUser(registrationData71);
        attorneyRepository71.save(a71);

        AttorneyRepository attorneyRepository81 = new AttorneyRepository();
        AttorneyFactory attorneyFactory81 = new AttorneyFactory();
        Set<String> professionals81 = new HashSet<>();
        professionals81.add("Work, Employment and Unemployment");
        RegistrationData registrationData81 = new RegistrationData("a81", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals81);
        registrationData81.setUserId(1234567816);
        Attorney a81 = attorneyFactory81.createUser(registrationData81);
        attorneyRepository81.save(a81);

        AttorneyRepository attorneyRepository91 = new AttorneyRepository();
        AttorneyFactory attorneyFactory91 = new AttorneyFactory();
        Set<String> professionals91 = new HashSet<>();
        professionals91.add("Juvenile");
        RegistrationData registrationData91 = new RegistrationData("a91", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals91);
        registrationData91.setUserId(1234567817);
        Attorney a91 = attorneyFactory91.createUser(registrationData91);
        attorneyRepository91.save(a91);

        AttorneyRepository attorneyRepository101 = new AttorneyRepository();
        AttorneyFactory attorneyFactory101 = new AttorneyFactory();
        Set<String> professionals101 = new HashSet<>();
        professionals101.add("Education");
        RegistrationData registrationData101 = new RegistrationData("a101", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals101);
        registrationData101.setUserId(1234567818);
        Attorney a101 = attorneyFactory101.createUser(registrationData101);
        attorneyRepository101.save(a101);

        AttorneyRepository attorneyRepository12 = new AttorneyRepository();
        AttorneyFactory attorneyFactory12 = new AttorneyFactory();
        Set<String> professionals12 = new HashSet<>();
        professionals12.add("Education");
        RegistrationData registrationData12 = new RegistrationData("a12", "ihan.yuan@mail.utoronto.ca",
                "12345678", "12345678", "CA", "12345", professionals12);
        registrationData12.setUserId(1234567819);
        Attorney a12 = attorneyFactory12.createUser(registrationData12);
        attorneyRepository12.save(a12);

    }
}
