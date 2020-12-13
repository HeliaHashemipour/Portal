import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentPanel {
    private final Student student;
    private final JFrame frame;
    private JPanel header;
    private JPanel menu;
    private JPanel mainPanel;
    private JPanel personalPanel;
    private JPanel mealPanel;
    private JPanel classRoomsPanel;


    public StudentPanel(Student st) {
        student = st;

        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 700);
        frame.setTitle("Students Panel");
        frame.setLayout(null);

        setHeader();

        setMainPanel();

        setMenu();

        setPersonalPanel();

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

        Label greeting = new Label(String.format("Welcome %s %s.", student.getFirstName(), student.getLastName()));
        Font font = new Font("Tahoma", Font.PLAIN, 20);
        greeting.setFont(font);
        greeting.setBounds(20, 50, 700, 20);

        header.add(greeting);

        frame.add(header);
    }

    private void setMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBounds(250, 125, 750, 575);
        mainPanel.setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        mainPanel.setBorder(border);

        frame.add(mainPanel);
    }

    private void setMenu() {

    }

    private void setPersonalPanel() {

    }

    private void setClassRoomsPanel() {

    }

    private Object[][] getMeals() {

    }

    private Object[][] getClassRooms() {
    }
}


