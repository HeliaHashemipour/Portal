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
    private void setClassRoomsPanel() {
        classRoomsPanel = new JPanel();
        classRoomsPanel.setBounds(0, 0, 750, 575);
        classRoomsPanel.setLayout(null);

        Object[][] data = getClassrooms();
        String[] columnNames = {"Name", "Units", "Capacity", "Time", "Day", "Delete"};

        TableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 700, 450);

        JButton btnAddClassroom = new JButton("Add Classroom");
        btnAddClassroom.setBounds(580, 480, 140, 30);
        btnAddClassroom.addActionListener(e -> addClassRoom());

        classRoomsPanel.add(btnAddClassroom);
        classRoomsPanel.add(sp);
        classRoomsPanel.setVisible(false);

        mainPanel.add(classRoomsPanel);
    }


    private void addClassRoom() {
        JFrame addingClassroom = new JFrame("Adding Classroom");
        addingClassroom.setLayout(null);
        addingClassroom.setBounds(300, 300, 370, 500);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(30, 50, 100, 30);

        JTextField txtName = new JTextField();
        txtName.setBounds(130, 50, 200, 30);

        JLabel lblNumberOfUnits = new JLabel("NumberOfUnits:");
        lblNumberOfUnits.setBounds(30, 110, 100, 30);

        JTextField txtNumberOfUnits = new JTextField();
        txtNumberOfUnits.setBounds(130, 110, 200, 30);

        JLabel lblCapacity = new JLabel("Capacity:");
        lblCapacity.setBounds(30, 170, 100, 30);

        JTextField txtCapacity = new JTextField();
        txtCapacity.setBounds(130, 170, 200, 30);

        JLabel lblTime = new JLabel("Time:");
        lblTime.setBounds(30, 230, 100, 30);

        JComboBox<ClassTime> cmbTime = new JComboBox<>();
        cmbTime.setBounds(130, 230, 200, 30);
        cmbTime.addItem(ClassTime.FIRST);
        cmbTime.addItem(ClassTime.SECOND);
        cmbTime.addItem(ClassTime.THIRD);
        cmbTime.addItem(ClassTime.FORTH);

        JLabel lblDay = new JLabel("Day:");
        lblDay.setBounds(30, 290, 100, 30);

        JComboBox<ClassDay> cmbDay = new JComboBox<>();
        cmbDay.setBounds(130, 290, 200, 30);
        cmbDay.addItem(ClassDay.SATURDAY);
        cmbDay.addItem(ClassDay.SUNDAY);
        cmbDay.addItem(ClassDay.MONDAY);
        cmbDay.addItem(ClassDay.TUESDAY);
        cmbDay.addItem(ClassDay.WEDNESDAY);

        JButton btnAdd = new JButton("Create");
        btnAdd.setBounds(230, 340, 100, 30);
        btnAdd.addActionListener(e -> {
            Classroom classroom = new Classroom();
            classroom.setName(txtName.getText());
            classroom.setNumberOfUnit(Integer.parseInt(txtNumberOfUnits.getText()));
            classroom.setCapacity(Integer.parseInt(txtCapacity.getText()));
            classroom.setClassTime(
                    switch (cmbTime.getSelectedIndex()) {
                        case 0 -> ClassTime.FIRST;
                        case 1 -> ClassTime.SECOND;
                        case 2 -> ClassTime.THIRD;
                        case 3 -> ClassTime.FORTH;
                        default -> null;
                    }
            );
            classroom.setClassDay(
                    switch (cmbDay.getSelectedIndex()) {
                        case 0 -> ClassDay.SATURDAY;
                        case 1 -> ClassDay.SUNDAY;
                        case 2 -> ClassDay.MONDAY;
                        case 3 -> ClassDay.TUESDAY;
                        case 4 -> ClassDay.WEDNESDAY;
                        default -> null;
                    }
            );
            classroom.setProfessor(professor);
            professor.createClassroom(classroom);
            addingClassroom.dispose();
            frame.dispose();
            new ProfessorPanel(professor);
        });


        addingClassroom.add(lblName);
        addingClassroom.add(lblNumberOfUnits);
        addingClassroom.add(lblCapacity);
        addingClassroom.add(lblDay);
        addingClassroom.add(lblTime);
        addingClassroom.add(txtName);
        addingClassroom.add(txtNumberOfUnits);
        addingClassroom.add(txtCapacity);
        addingClassroom.add(cmbDay);
        addingClassroom.add(cmbTime);
        addingClassroom.add(btnAdd);

        addingClassroom.setVisible(true);
    }
    private Object[][] getClassrooms() {
        List<Classroom> classrooms = professor.getClassrooms();
        Object[][] data = new Object[classrooms.size()][6];
        for (int i = 0; i < classrooms.size(); i++) {
            data[i][0] = classrooms.get(i).getName();
            data[i][1] = classrooms.get(i).getNumberOfUnit();
            data[i][2] = classrooms.get(i).getCapacity();
            data[i][3] = classrooms.get(i).getClassTime();
            data[i][4] = classrooms.get(i).getClassDay();
            JButton delete = new JButton("Delete");
            int finalI = i;
            delete.addActionListener(e -> {
                professor.removeClassroom(classrooms.get(finalI));
                frame.dispose();
                new ProfessorPanel(professor);
            });
            data[i][5] = delete;
        }
        return data;
    }

    private Object[][] getStudents(Classroom classroom) {
        if (classroom == null)
            return null;
        List<Student> students = classroom.getStudents();
        Object[][] data = new Object[students.size()][4];
        for (int i = 0; i < students.size(); i++) {
            data[i][0] = students.get(i).getFirstName();
            data[i][1] = students.get(i).getLastName();
            data[i][2] = students.get(i).getId();
            JButton btnSetGrade = new JButton("Set");
            int finalI = i;
            btnSetGrade.addActionListener(e -> setStudentsGrade(students.get(finalI), classroom));
            data[i][3] = btnSetGrade;
        }
        return data;
    }


    private void setStudentsGrade(Student st, Classroom classroom) {
        JFrame setGradeFrame = new JFrame(String.format("%s %s", st.getFirstName(), st.getLastName()));
        setGradeFrame.setLayout(null);
        setGradeFrame.setBounds(300, 300, 370, 200);

        JLabel lblGrade = new JLabel("Grade:");
        lblGrade.setBounds(30, 50, 100, 30);

        JTextField txtGrade = new JTextField();
        txtGrade.setBounds(130, 50, 200, 30);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(135, 130, 100, 30);
        btnSubmit.addActionListener(e -> {
            List<Unit> units = st.getUnits();
            for (Unit unit : units) {
                if (unit.getClassroom().equals(classroom))
                    unit.setGrade(Integer.parseInt(txtGrade.getText()));
            }
        });

        setGradeFrame.add(lblGrade);
        setGradeFrame.add(txtGrade);
        setGradeFrame.add(btnSubmit);

        setGradeFrame.setVisible(true);
        setGradeFrame.setResizable(false);
    }

}


