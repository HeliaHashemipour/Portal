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
        header = new JPanel();
        header.setBounds(0, 0, 1000, 125);
        header.setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        header.setBorder(border);

        Label greeting = new Label(String.format("Welcome %s %s.", professor.getFirstName(), professor.getLastName()));
        Font font = new Font("Tahoma", Font.PLAIN, 20);
        greeting.setFont(font);
        greeting.setBounds(20, 50, 700, 20);

        header.add(greeting);

        frame.add(header);
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


