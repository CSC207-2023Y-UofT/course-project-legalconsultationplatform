package screen;
import javax.swing.JFrame;
/**
 *
 * @author qhc
 */
public class UImain {
    public static int userId;
    public static String userType;

    public UImain(int userId, String userType) {
        this.userId = userId;
        this.userType = userType;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(userType.equals("Attorney")) {
            JFrame attorneyWindow = new AttorneyHomePage(userId);
            attorneyWindow.setLocationByPlatform(true);
            attorneyWindow.setVisible(true);
        }
        else{
            JFrame clientWindow = new ClientHomePage(userId);
            clientWindow.setLocationByPlatform(true);
            clientWindow.setVisible(true);
        }
    }

}