import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * This class designs and shows the login page of this application.
 * @Hashemipour
 * @since 2020
 */
public class LoginPage {

    private JFrame frame;
    private JPanel panel;
    private JTextField txtId, txtPassword;
    private JComboBox<String> cmbRole;
    private JButton btnSubmit;

    /**
     * The constructor of this method that sets all of the components.
     */
    public LoginPage() {
        frame = new JFrame("Login");
        frame.setBounds(650, 300, 410, 410);
        frame.setLayout(null);
        JPanel background = new JPanel(null);
        background.setBounds(0, 0, 500, 500);

        panel = new JPanel(null);
        panel.setBounds(50, 90, 300, 200);

        setComponents();

        ImageIcon icon = null;

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("src/image/LoginPage.JPG"));
            Image image = bufferedImage.getScaledInstance(410, 410, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel lblIcon = new JLabel();
        lblIcon.setIcon(icon);
        lblIcon.setBounds(0, 0, 410, 410);


        background.add(panel);
        background.add(lblIcon);
        frame.add(background);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * This method sets componenets of the login page
     */
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

    /**
     * Thsi method logs in if there is a person with the given id and password and role.
     * @param id
     * @param password
     * @param role
     * @return
     */
    private int login(String id, String password, String role) {
        switch (role) {
            case "Admin":
                Admin admin = FileInterface.getAdmin(id);
                if (admin == null)
                    return -1;
                if (!admin.getPassword().equals(password))
                    return 0;
                new AdminPanel(admin);
                return 1;
            case "Professor":
                Professor professor = FileInterface.getProfessor(id);
                if (professor == null)
                    return -1;
                if (!professor.getPassword().equals(password))
                    return 0;
                new ProfessorPanel(professor);
                return 1;
            case "Student":
                Student student = FileInterface.getStudent(id);
                if (student == null)
                    return -1;
                if (!student.getPassword().equals(password))
                    return 0;
                new StudentPanel(student);
                return 1;
            default:
                return -1;
        }
    }
}
