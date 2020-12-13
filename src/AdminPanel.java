import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class AdminPanel {
    private JFrame frame;
    private JPanel header;
    private JPanel menu;
    private JPanel mainPanel;
    private JPanel personalPanel;
    private JPanel mealPanel;
    private JPanel studentsPanel;
    private JPanel professorsPanel;
    private JPanel classRoomsPanel;
    private final Admin admin;

    public AdminPanel(Admin admin) {
        this.admin = admin;
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 700);
        frame.setTitle("Admin Panel");
        frame.setLayout(null);

        setHeader();

        setMainPanel();

        setMenu();

        setPersonalPanel();

        setProfessorsPanel();

        setStudentsPanel();

        setMealPanel();

        setClassRoomsPanel();

        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private void setHeader() {

    }

    private void setMenu() {

    }

    private void setMainPanel() {

    }

    private void setPersonalPanel() {

    }

    private void setMealPanel() {

    }

    private void setStudentsPanel() {

    }

    private void setProfessorsPanel() {

    }

    private void setClassRoomsPanel() {

    }

    private String[][] getStudents() {

    }

    private String[][] getProfessors() {

    }

    private void addStudent() {

    }

    private void addProfessor() {


    }

    private String[][] getClassrooms() {

    }


}


