import driver.database.AttorneyRepository;
import entity.Attorney;

public class AttorneyRegister {
    public static void main(String[] args) {
        AttorneyRepository attorneyRepository = new AttorneyRepository();
        Attorney attorney1 = new Attorney();
        attorneyRepository.save(attorney1);
    }
}
