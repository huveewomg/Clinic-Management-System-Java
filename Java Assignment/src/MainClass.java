import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Window;

public class MainClass {

    private static JFrame frame;

    public static void SettingForm(String username) {
        SettingPage settingPage = new SettingPage(username);
        settingPage.setVisible(true);
    }

    public static void Logout() {
        for (Window window : Window.getWindows()) {
            window.dispose();
        }
        LoginForm loginForm = new LoginForm(false);
        loginForm.setVisible(true);
        JOptionPane.showMessageDialog(null, "Logout Successfully Thank You For using The System!");

    }
}
