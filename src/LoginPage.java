import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoginPage {

    private JFrame frame;
    private JPanel panel;
    private JTextField txtId, txtPassword;
    private JComboBox<String> cmbRole;
    private JButton btnSubmit;

    public LoginPage() {
        frame = new JFrame("Login");
        frame.setBounds(100, 100, 410, 410);
        frame.setLayout(null);
        JPanel background = new JPanel(null);
        background.setBounds(0, 0, 500, 500);
        background.setBackground(Color.GRAY);

        panel = new JPanel(null);
        panel.setBounds(50, 90, 300, 200);

        setComponents();


        background.add(panel);
        frame.add(background);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setComponents() {

    }

    private int login(String id, String password, String role) {
    }
}
