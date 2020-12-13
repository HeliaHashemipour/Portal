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
        JLabel lblId, lblPassword, lblRole;
        lblId = new JLabel("Username:");
        lblId.setBounds(10, 40, 100, 20);
        panel.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 40, 170, 23);
        panel.add(txtId);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(10, 80, 100, 20);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 80, 170, 23);
        panel.add(txtPassword);

        lblRole = new JLabel("Role:");
        lblRole.setBounds(10, 120, 100, 20);
        panel.add(lblRole);

        cmbRole = new JComboBox<>();
        cmbRole.addItem("Admin");
        cmbRole.addItem("Professor");
        cmbRole.addItem("Student");
        cmbRole.setBounds(100, 120, 170, 23);
        panel.add(cmbRole);

        btnSubmit = new JButton("Login");
        btnSubmit.setBounds(190, 160, 90, 25);
        btnSubmit.addActionListener(e -> {
            int login = login(txtId.getText(), txtPassword.getText(), cmbRole.getSelectedItem().toString());
            Border border = BorderFactory.createLineBorder(Color.RED);
            switch (login) {
                case -1:
                    txtId.setBorder(border);
                    break;
                case 0:
                    txtPassword.setBorder(border);
                    break;
                case 1:
                    frame.dispose();
                    break;
            }
        });
        panel.add(btnSubmit);
    }

    private int login(String id, String password, String role) {
    }
}
