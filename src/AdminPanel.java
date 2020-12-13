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


