import javax.swing.*;
import java.awt.*;

public abstract class BaseFrame extends JFrame {
    public BaseFrame(boolean disposeOnClose) {
        if (disposeOnClose) {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        setBounds(550, 300, 800, 600);
        setLocationRelativeTo(null); 
        initialize();
    }
    protected abstract void initialize(); 
}