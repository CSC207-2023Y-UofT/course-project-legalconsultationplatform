package screen;
import javax.swing.JFrame;
/**
 *
 * @author qhc
 */
public class UImain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame mainwindow = new AttorneyHomePage();
        mainwindow.setLocationByPlatform(true);
        mainwindow.setVisible(true);
    }

}