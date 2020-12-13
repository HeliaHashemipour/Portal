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
        menu = new JPanel();
        menu.setBounds(0, 125, 250, 575);
        menu.setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        menu.setBorder(border);

        JButton showPersonalInfo = new JButton("Change Personal Info");
        showPersonalInfo.setBounds(20, 20, 210, 40);
        showPersonalInfo.addActionListener(e -> {
            personalPanel.setVisible(true);
            studentsPanel.setVisible(false);
            classRoomsPanel.setVisible(false);
        });

        JButton seeStudents = new JButton("Students List");
        seeStudents.setBounds(20, 80, 210, 40);
        seeStudents.addActionListener(e -> {
            personalPanel.setVisible(false);
            studentsPanel.setVisible(true);
            classRoomsPanel.setVisible(false);
        });

        JButton seeClassRooms = new JButton("Classrooms List");
        seeClassRooms.setBounds(20, 140, 210, 40);
        seeClassRooms.addActionListener(e -> {
            personalPanel.setVisible(false);
            studentsPanel.setVisible(false);
            classRoomsPanel.setVisible(true);
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(20, 200, 210, 40);
        logout.addActionListener(e -> {
            frame.dispose();
            new LoginPage();
        });

        menu.add(showPersonalInfo);
        menu.add(seeStudents);
        menu.add(seeClassRooms);
        menu.add(logout);

        frame.add(menu);
    }


    private void setMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBounds(250, 125, 750, 575);
        mainPanel.setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        mainPanel.setBorder(border);

        frame.add(mainPanel);
    }

    private void setPersonalPanel() {
        personalPanel = new JPanel();
        personalPanel.setBounds(0, 0, 750, 575);
        personalPanel.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(30, 100, 150, 40);

        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(120, 105, 200, 30);
        txtUsername.setText(professor.getId());

        JButton btnChangeUsername = new JButton("Change");


        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(30, 200, 150, 40);

        JTextField txtPassword = new JTextField(professor.getPassword());
        txtPassword.setBounds(120, 205, 200, 30);

        JButton btnChangePassword = new JButton("Change");
        btnChangePassword.setBounds(330, 205, 100, 30);
        btnChangePassword.addActionListener(e -> {
            professor.setPassword(txtPassword.getText());
        });

        personalPanel.add(lblUsername);
        personalPanel.add(txtUsername);
        personalPanel.add(btnChangeUsername);
        personalPanel.add(btnChangePassword);
        personalPanel.add(lblPassword);
        personalPanel.add(txtPassword);

        personalPanel.setVisible(false);

        mainPanel.add(personalPanel);
    }


    private void setStudentsPanel() {
        studentsPanel = new JPanel();
        studentsPanel.setLayout(null);
        studentsPanel.setBounds(0, 0, 750, 575);

        Object[][] data = getStudents(professor.getClassrooms().size() == 0 ? null : professor.getClassrooms().get(0));
        String[] columnNames = {"FirstName", "LastName", "StudentID", "SetGrade"};

        JLabel lblClassroom = new JLabel("Classroom");
        lblClassroom.setBounds(20, 10, 100, 30);

        JComboBox<Classroom> cmbClassrooms = new JComboBox<>();
        for (Classroom classroom : professor.getClassrooms()) {
            cmbClassrooms.addItem(classroom);
        }
        cmbClassrooms.setBounds(100, 10, 150, 30);


        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable studentsTable = new JTable(model);

        cmbClassrooms.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                studentsTable.setModel(new DefaultTableModel(getStudents((Classroom) e.getItem()), columnNames));
                studentsTable.repaint();
            }
        });

        JScrollPane sp = new JScrollPane(studentsTable);
        sp.setBounds(20, 60, 700, 460);

        studentsPanel.add(lblClassroom);
        studentsPanel.add(cmbClassrooms);
        studentsPanel.add(sp);
        studentsPanel.setVisible(false);

        mainPanel.add(studentsPanel);
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


