import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Window;

public class MainClass {

    private static JFrame frame;

    public static void SettingForm() {
        SettingPage settingPage = new SettingPage();
        settingPage.setVisible(true);
    }

    public static void Logout() {
        UserSession.getInstance().setUsername(null);
        UserSession.getInstance().setPassword(null);
        for (Window window : Window.getWindows()) {
            window.dispose();
        }
        LoginForm loginForm = new LoginForm(false);
        loginForm.setVisible(true);
        JOptionPane.showMessageDialog(null, "Logout Successfully Thank You For using The System!");


    }
}
