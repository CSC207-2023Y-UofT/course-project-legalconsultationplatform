package userentities;

public class Attorney implements User{

    private final int userID;
    private final String password;

    public Attorney(int userID, String password) {
        this.userID = userID;
        this.password = password;
    }
    @Override
    public int getUserID() {
        return userID;
    }
    @Override
    public String getPassword() {
        return password;
    }
}
