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
        header = new JPanel();
        header.setBounds(0, 0, 1000, 125);
        header.setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        header.setBorder(border);

        Label greeting = new Label(String.format("Welcome %s %s.", admin.getFirstName(), admin.getLastName()));
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
            professorsPanel.setVisible(false);
            studentsPanel.setVisible(false);
            mealPanel.setVisible(false);
            classRoomsPanel.setVisible(false);
        });

        JButton setMealPlan = new JButton("Change Meal Plan");
        setMealPlan.setBounds(20, 80, 210, 40);
        setMealPlan.addActionListener(e -> {
            personalPanel.setVisible(false);
            professorsPanel.setVisible(false);
            studentsPanel.setVisible(false);
            mealPanel.setVisible(true);
            classRoomsPanel.setVisible(false);
        });

        JButton seeStudents = new JButton("Students List");
        seeStudents.setBounds(20, 140, 210, 40);
        seeStudents.addActionListener(e -> {
            personalPanel.setVisible(false);
            professorsPanel.setVisible(false);
            studentsPanel.setVisible(true);
            mealPanel.setVisible(false);
            classRoomsPanel.setVisible(false);
        });

        JButton seeProfessor = new JButton("Professors List");
        seeProfessor.setBounds(20, 200, 210, 40);
        seeProfessor.addActionListener(e -> {
            personalPanel.setVisible(false);
            professorsPanel.setVisible(true);
            studentsPanel.setVisible(false);
            mealPanel.setVisible(false);
            classRoomsPanel.setVisible(false);
        });

        JButton seeClassRooms = new JButton("Classrooms List");
        seeClassRooms.setBounds(20, 260, 210, 40);
        seeClassRooms.addActionListener(e -> {
            personalPanel.setVisible(false);
            professorsPanel.setVisible(false);
            studentsPanel.setVisible(false);
            mealPanel.setVisible(false);
            classRoomsPanel.setVisible(true);
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(20, 320, 210, 40);
        logout.addActionListener(e -> {
            frame.dispose();
            new LoginPage();
        });

        menu.add(showPersonalInfo);
        menu.add(setMealPlan);
        menu.add(seeStudents);
        menu.add(seeProfessor);
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
        txtUsername.setText(admin.getId());

        JButton btnChangeUsername = new JButton("Change");

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(30, 200, 150, 40);

        JTextField txtPassword = new JTextField(admin.getPassword());
        txtPassword.setBounds(120, 205, 200, 30);

        JButton btnChangePassword = new JButton("Change");
        btnChangePassword.setBounds(330, 205, 100, 30);
        btnChangePassword.addActionListener(e -> {
            admin.setPassword(txtPassword.getText());
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

    private void setMealPanel() {
        mealPanel = new JPanel();
        mealPanel.setBounds(0, 0, 750, 575);
        mealPanel.setLayout(null);

        JLabel[] lblMealNames = new JLabel[7];
        JLabel[] lblMealCosts = new JLabel[7];
        JTextField[] txtMealNames = new JTextField[7];
        JTextField[] txtMealCosts = new JTextField[7];

        for (int i = 0; i < 7; i++) {
            lblMealNames[i] = new JLabel("Name");
            lblMealNames[i].setBounds(30, 50 + 60 * i, 100, 40);

            txtMealNames[i] = new JTextField();
            txtMealNames[i].setBounds(80, 56 + 60 * i, 200, 30);

            lblMealCosts[i] = new JLabel("Cost");
            lblMealCosts[i].setBounds(320, 50 + 60 * i, 100, 40);

            txtMealCosts[i] = new JTextField();
            txtMealCosts[i].setBounds(370, 56 + 60 * i, 200, 30);

            mealPanel.add(lblMealNames[i]);
            mealPanel.add(txtMealNames[i]);
            mealPanel.add(lblMealCosts[i]);
            mealPanel.add(txtMealCosts[i]);
        }

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(620, 480, 90, 30);
        btnCancel.addActionListener(e -> {
            Meal[] meals = MealPlan.getMeals();
            for (int i = 0; i < meals.length; i++) {
                txtMealNames[i].setText(meals[i].getName());
                txtMealCosts[i].setText(String.valueOf(meals[i].getCost()));
            }
        });

        JButton btnApply = new JButton("Apply");
        btnApply.setBounds(510, 480, 90, 30);
        btnApply.addActionListener(e -> {
            Meal[] meals = new Meal[7];
            for (int i = 0; i < 7; i++) {
                meals[i].setName(txtMealNames[i].getText());
                meals[i].setCost(Integer.parseInt(txtMealCosts[i].getText()));
            }
            MealPlan.setMeals(meals);
        });

        mealPanel.add(btnCancel);
        mealPanel.add(btnApply);

        mealPanel.setVisible(false);

        mainPanel.add(mealPanel);

    }

    private void setStudentsPanel() {
        studentsPanel = new JPanel();
        studentsPanel.setLayout(null);
        studentsPanel.setBounds(0, 0, 750, 575);

        String[][] data = getStudents();
        String[] columnNames = {"FirstName", "LastName", "StudentID", "Password", "AverageGrade"};

        TableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable studentsTable = new JTable(model);

        JScrollPane sp = new JScrollPane(studentsTable);
        sp.setBounds(20, 20, 700, 460);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(e -> addStudent());
        btnAdd.setBounds(640, 495, 80, 30);

        studentsPanel.add(sp);
        studentsPanel.add(btnAdd);
        studentsPanel.setVisible(false);

        mainPanel.add(studentsPanel);
    }

    private void setProfessorsPanel() {
        professorsPanel = new JPanel();
        professorsPanel.setBounds(0, 0, 750, 575);
        professorsPanel.setLayout(null);

        String[][] data = getProfessors();
        String[] columnNames = {"FirstName", "LastName", "ID", "Password"};

        TableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 700, 460);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(e -> addProfessor());
        btnAdd.setBounds(640, 495, 80, 30);

        professorsPanel.add(sp);
        professorsPanel.add(btnAdd);
        professorsPanel.setVisible(false);

        mainPanel.add(professorsPanel);
    }
    private String[][] getStudents() {
        List<Student> students = FileInterface.allStudents();
        String[][] data = new String[students.size()][5];
        for (int i = 0; i < students.size(); i++) {
            data[i][0] = students.get(i).getFirstName();
            data[i][1] = students.get(i).getLastName();
            data[i][2] = students.get(i).getId();
            data[i][3] = students.get(i).getPassword();
            data[i][4] = String.valueOf(students.get(i).averageGrade());
        }
        return data;
    }

    private String[][] getProfessors() {
        List<Professor> professors = FileInterface.allProfessor();
        String[][] data = new String[professors.size()][4];
        for (int i = 0; i < professors.size(); i++) {
            data[i][0] = professors.get(i).getFirstName();
            data[i][1] = professors.get(i).getLastName();
            data[i][2] = professors.get(i).getId();
            data[i][3] = professors.get(i).getPassword();
        }
        return data;
    }

    private void setClassRoomsPanel() {
        classRoomsPanel = new JPanel();
        classRoomsPanel.setBounds(0, 0, 750, 575);
        classRoomsPanel.setLayout(null);

        String[][] data = getClassrooms();
        String[] columnNames = {"Name", "Units", "Capacity", "Time", "Day", "Professor"};

        TableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 700, 490);

        classRoomsPanel.add(sp);
        classRoomsPanel.setVisible(false);

        mainPanel.add(classRoomsPanel);
    }


    private void addStudent() {
        JFrame addingStudentFrame = new JFrame("Adding student");
        addingStudentFrame.setLayout(null);
        addingStudentFrame.setBounds(300, 300, 370, 370);

        JLabel lblFirstName = new JLabel("FirstName:");
        lblFirstName.setBounds(30, 50, 100, 30);

        JTextField txtFirstName = new JTextField();
        txtFirstName.setBounds(130, 50, 200, 30);

        JLabel lblLastName = new JLabel("LastName:");
        lblLastName.setBounds(30, 110, 100, 30);

        JTextField txtLastName = new JTextField();
        txtLastName.setBounds(130, 110, 200, 30);

        JLabel lblId = new JLabel("StudentID:");
        lblId.setBounds(30, 170, 100, 30);

        JTextField txtId = new JTextField();
        txtId.setBounds(130, 170, 200, 30);

        JLabel lblPasswords = new JLabel("Password:");
        lblPasswords.setBounds(30, 230, 100, 30);

        JTextField txtPassword = new JTextField();
        txtPassword.setBounds(130, 230, 200, 30);

        JButton btnAdd = new JButton("Create");
        btnAdd.setBounds(230, 280, 100, 30);
        btnAdd.addActionListener(e -> {
            Student st = new Student();
            st.setFirstName(txtFirstName.getText());
            st.setLastName(txtLastName.getText());
            st.setId(txtId.getText());
            st.setPassword(txtPassword.getText());
            admin.addStudent(st);
            addingStudentFrame.dispose();
            frame.dispose();
            new AdminPanel(admin);
        });


        addingStudentFrame.add(lblFirstName);
        addingStudentFrame.add(lblLastName);
        addingStudentFrame.add(lblId);
        addingStudentFrame.add(lblPasswords);
        addingStudentFrame.add(txtFirstName);
        addingStudentFrame.add(txtLastName);
        addingStudentFrame.add(txtId);
        addingStudentFrame.add(txtPassword);
        addingStudentFrame.add(btnAdd);

        addingStudentFrame.setVisible(true);
    }

    private void addProfessor() {
        JFrame addingProfessorFrame = new JFrame("Adding student");
        addingProfessorFrame.setLayout(null);
        addingProfessorFrame.setBounds(300, 300, 370, 370);

        JLabel lblFirstName = new JLabel("FirstName:");
        lblFirstName.setBounds(30, 50, 100, 30);

        JTextField txtFirstName = new JTextField();
        txtFirstName.setBounds(130, 50, 200, 30);

        JLabel lblLastName = new JLabel("LastName:");
        lblLastName.setBounds(30, 110, 100, 30);

        JTextField txtLastName = new JTextField();
        txtLastName.setBounds(130, 110, 200, 30);

        JLabel lblId = new JLabel("StudentID:");
        lblId.setBounds(30, 170, 100, 30);

        JTextField txtId = new JTextField();
        txtId.setBounds(130, 170, 200, 30);

        JLabel lblPasswords = new JLabel("Password:");
        lblPasswords.setBounds(30, 230, 100, 30);

        JTextField txtPassword = new JTextField();
        txtPassword.setBounds(130, 230, 200, 30);

        JButton btnAdd = new JButton("Create");
        btnAdd.setBounds(230, 280, 100, 30);
        btnAdd.addActionListener(e -> {
            Professor professor = new Professor();
            professor.setFirstName(txtFirstName.getText());
            professor.setLastName(txtLastName.getText());
            professor.setId(txtId.getText());
            professor.setPassword(txtPassword.getText());
            admin.addProfessor(professor);
            addingProfessorFrame.dispose();
            frame.dispose();
            new AdminPanel(admin);
        });

        addingProfessorFrame.add(lblFirstName);
        addingProfessorFrame.add(lblLastName);
        addingProfessorFrame.add(lblId);
        addingProfessorFrame.add(lblPasswords);
        addingProfessorFrame.add(txtFirstName);
        addingProfessorFrame.add(txtLastName);
        addingProfessorFrame.add(txtId);
        addingProfessorFrame.add(txtPassword);
        addingProfessorFrame.add(btnAdd);

        addingProfessorFrame.setVisible(true);

    }

    private String[][] getClassrooms() {
        List<Classroom> classrooms = FileInterface.allClassrooms();
        String[][] data = new String[classrooms.size()][6];
        for (int i = 0; i < classrooms.size(); i++) {
            data[i][0] = classrooms.get(i).getName();
            data[i][1] = String.valueOf(classrooms.get(i).getNumberOfUnit());
            data[i][2] = String.valueOf(classrooms.get(i).getCapacity());
            data[i][3] = classrooms.get(i).getClassTime().toString();
            data[i][4] = classrooms.get(i).getClassDay().toString();
            data[i][5] = "Prof. " + classrooms.get(i).getProfessor().getLastName();
        }
        return data;
    }

}


