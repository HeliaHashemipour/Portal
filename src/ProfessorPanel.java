import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;

public class ProfessorPanel {

    private Professor professor;
    private JFrame frame;
    private JPanel header;
    private JPanel menu;
    private JPanel mainPanel;
    private JPanel personalPanel;
    private JPanel classRoomsPanel;
    private JPanel studentsPanel;

    public ProfessorPanel(Professor professor) {
        this.professor = professor;

        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 700);
        frame.setTitle("Professors Panel");
        frame.setLayout(null);

        setHeader();

        setMainPanel();

        setMenu();

        setStudentsPanel();

        setClassRoomsPanel();

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setHeader() {

    }

    private void setMenu() {

    }

    private void setMainPanel() {

    }

    private void setStudentsPanel() {

    }

    private void setClassRoomsPanel() {

    }

    private void addClassRoom() {

    }

    private Object[][] getClassrooms() {

    }

    private Object[][] getStudents(Classroom classroom) {

    }

    private void setStudentsGrade(Student st, Classroom classroom) {

    }

}


