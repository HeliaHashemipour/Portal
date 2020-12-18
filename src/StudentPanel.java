import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class show a panel for every student.
 * @Hashemipour
 * @since 2020
 */
public class StudentPanel {

    private final Student student;
    private final JFrame frame;
    private JPanel header;
    private JPanel menu;
    private JPanel mainPanel;
    private JPanel personalPanel;
    private JPanel mealPanel;
    private JPanel classRoomsPanel;
    private JPanel balancePanel;
    private JTextField txtBalance;

    /**
     * The constructor of this class that puts every component on its location.
     * @param st
     */
    public StudentPanel(Student st) {
        student = st;
        for (Unit unit : st.getUnits())
            System.out.println(unit.getClassroom().getName());
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 700);
        frame.setTitle("Students Panel");
        frame.setLayout(null);

        setHeader();

        setMainPanel();

        setMenu();

        setPersonalPanel();

        setMealPanel();

        setClassRoomsPanel();

        setBalancePanel();

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * This method sets the header of this panel
     */
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

        JLabel lblIcon = new JLabel();
        lblIcon.setBounds(887, 13, 100, 100);

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("src/image/Student.JPG"));
            Image image = bufferedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(image);
            lblIcon.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        header.add(greeting);
        header.add(lblIcon);

        frame.add(header);
    }

    /**
     * This method sets the main panel of studetns panel
     */
    private void setMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBounds(250, 125, 750, 575);
        mainPanel.setLayout(null);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        mainPanel.setBorder(border);

        frame.add(mainPanel);
    }

    /**
     * This method sets the menu of this student.
     */
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
            mealPanel.setVisible(false);
            classRoomsPanel.setVisible(false);
            balancePanel.setVisible(false);
        });

        JButton setMealPlan = new JButton("Show Meal Plan");
        setMealPlan.setBounds(20, 80, 210, 40);
        setMealPlan.addActionListener(e -> {
            personalPanel.setVisible(false);
            mealPanel.setVisible(true);
            classRoomsPanel.setVisible(false);
            balancePanel.setVisible(false);
        });

        JButton seeClassRooms = new JButton("Classrooms List");
        seeClassRooms.setBounds(20, 140, 210, 40);
        seeClassRooms.addActionListener(e -> {
            personalPanel.setVisible(false);
            mealPanel.setVisible(false);
            classRoomsPanel.setVisible(true);
            balancePanel.setVisible(false);
        });

        JButton seeBalance = new JButton("Account Balance");
        seeBalance.setBounds(20, 200, 210, 40);
        seeBalance.addActionListener(e -> {
            personalPanel.setVisible(false);
            mealPanel.setVisible(false);
            classRoomsPanel.setVisible(false);
            balancePanel.setVisible(true);
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(20, 260, 210, 40);
        logout.addActionListener(e -> {
            FileInterface.updateStudent(student);
            frame.dispose();
            new LoginPage();
        });

        menu.add(showPersonalInfo);
        menu.add(setMealPlan);
        menu.add(seeClassRooms);
        menu.add(seeBalance);
        menu.add(logout);

        frame.add(menu);
    }

    /**
     * This metohd sets the personal panel of this studetn with its information.
     */
    private void setPersonalPanel() {
        personalPanel = new JPanel();
        personalPanel.setBounds(0, 0, 750, 575);
        personalPanel.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(30, 100, 150, 40);

        JTextField txtUsername = new JTextField();
        txtUsername.setBounds(120, 105, 200, 30);
        txtUsername.setText(student .getId());

        JButton btnChangeUsername = new JButton("Change");
        btnChangeUsername.setBounds(330, 105, 100, 30);
        btnChangeUsername.addActionListener(e -> {
            if (FileInterface.exists(txtUsername.getText(), new Admin())) {
                txtUsername.setText("This username exist!");
            } else {
                FileInterface.updateStudentsId(student.getId(), txtUsername.getText());
                student.setId(txtUsername.getText());
            }
        });

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(30, 200, 150, 40);

        JTextField txtPassword = new JTextField(student.getPassword());
        txtPassword.setBounds(120, 205, 200, 30);

        JButton btnChangePassword = new JButton("Change");
        btnChangePassword.setBounds(330, 205, 100, 30);
        btnChangePassword.addActionListener(e -> student.setPassword(txtPassword.getText()));

        JLabel lblGrade = new JLabel("Grade");
        lblGrade.setBounds(30, 300, 100, 30);

        JTextField txtGrade = new JTextField();
        txtGrade.setBounds(120, 300, 200, 30);
        txtGrade.setEnabled(false);
        txtGrade.setText(student.averageGrade() + "");

        JLabel lblUnits = new JLabel("Units");
        lblUnits.setBounds(30, 400, 100, 30);

        JTextField txtUnits = new JTextField();
        txtUnits.setBounds(120, 400, 200, 30);
        txtUnits.setText(student.getNumberOfUnits() + "");
        txtUnits.setEnabled(false);

        personalPanel.add(lblUsername);
        personalPanel.add(txtUsername);
        personalPanel.add(btnChangeUsername);
        personalPanel.add(btnChangePassword);
        personalPanel.add(lblPassword);
        personalPanel.add(lblGrade);
        personalPanel.add(txtGrade);
        personalPanel.add(txtPassword);
        personalPanel.add(lblUnits);
        personalPanel.add(txtUnits);

        personalPanel.setVisible(false);

        mainPanel.add(personalPanel);
    }

    /**
     * This method sets the meal panel of this student with the meal plan's information.
     */
    private void setMealPanel() {
        mealPanel = new JPanel();
        mealPanel.setBounds(0, 0, 750, 575);
        mealPanel.setLayout(null);

        Object[][] data = getMeals();

        String[] columnNames = {"Day", "Meal", "Cost", "Reserve"};

        TableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable studentsTable = new JTable(model);

        studentsTable.setFillsViewportHeight(true);

        TableCellRenderer renderer = (table, value, isSelected, hasFocus, row, column) -> (JButton) value;

        studentsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = studentsTable.rowAtPoint(e.getPoint());
                int column = studentsTable.columnAtPoint(e.getPoint());
                if (column == 3 && student.reserveMeal(MealPlan.getMeals()[row])) {
                    mealPanel.setVisible(false);
                    balancePanel.setVisible(true);
                    txtBalance.setText(student.getBalance() + "");
                } else {
                    System.out.println("Not reserved");
                }
            }
        });

        studentsTable.getColumn("Reserve").setCellRenderer(renderer);

        JScrollPane sp = new JScrollPane(studentsTable);
        sp.setBounds(20, 20, 700, 460);

        mealPanel.add(sp);
        mealPanel.setVisible(false);

        mainPanel.add(mealPanel);
    }

    /**
     * This method shows the classrooms of the student in a table.
     */
    private void setClassRoomsPanel() {
        classRoomsPanel = new JPanel();
        classRoomsPanel.setLayout(null);
        classRoomsPanel.setBounds(0, 0, 750, 575);

        Object[][] data = getClassRooms();

        String[] columnNames = {"Title", "Number Of Units", "Professor", "Class Time", "Class Day", "Grade", "Delete"};

        TableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable jTable = new JTable(model);

        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jTable.rowAtPoint(e.getPoint());
                int column = jTable.columnAtPoint(e.getPoint());
                if (column == 6) {
                    Unit unit = student.getUnits().remove(row);
                    unit.getClassroom().getStudents().remove(student);
                    frame.dispose();
                    new StudentPanel(student);
                }
            }
        });

        TableCellRenderer renderer = (table, value, isSelected, hasFocus, row, column) -> (JButton) value;

        jTable.getColumn("Delete").setCellRenderer(renderer);

        JScrollPane sp = new JScrollPane(jTable);
        sp.setBounds(20, 20, 700, 460);

        JButton addClassroom = new JButton("Add Unit");
        addClassroom.setBounds(620, 495, 100, 30);
        addClassroom.addActionListener(e -> addClassroom());

        classRoomsPanel.setVisible(false);
        classRoomsPanel.add(sp);
        classRoomsPanel.add(addClassroom);

        mainPanel.add(classRoomsPanel);
    }

    /**
     * This method sets the balance panel for the student.
     */
    private void setBalancePanel() {
        balancePanel = new JPanel();
        balancePanel.setLayout(null);
        balancePanel.setBounds(0, 0, 750, 575);

        JLabel lblBalance = new JLabel("Balance");
        lblBalance.setBounds(30, 100, 100, 30);

        txtBalance = new JTextField();
        txtBalance.setText(student.getBalance() + "");
        txtBalance.setBounds(120, 100, 200, 30);
        txtBalance.setEditable(false);

        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setBounds(30, 400, 100, 30);
        lblAmount.setVisible(false);

        JTextField txtAmount = new JTextField();
        txtAmount.setBounds(120, 400, 200, 30);
        txtAmount.setVisible(false);

        JLabel lblAccountNumber = new JLabel("AccountNumber");
        lblAccountNumber.setBounds(30, 200, 100, 30);
        lblAccountNumber.setVisible(false);

        JTextField txtAccountNumber = new JTextField();
        txtAccountNumber.setBounds(120, 200, 200, 30);
        txtAccountNumber.setVisible(false);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(30, 300, 100, 30);
        lblPassword.setVisible(false);

        JTextField txtPassword = new JTextField();
        txtPassword.setBounds(120, 300, 200, 30);
        txtPassword.setVisible(false);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(330, 400, 100, 30);
        btnSubmit.setVisible(false);

        JButton btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(330, 100, 100, 30);
        btnDeposit.addActionListener(e -> {
            lblAmount.setVisible(true);
            txtAmount.setVisible(true);
            btnSubmit.setVisible(true);
            lblAccountNumber.setVisible(true);
            txtAccountNumber.setVisible(true);
            lblPassword.setVisible(true);
            txtPassword.setVisible(true);
            btnDeposit.setEnabled(false);
        });

        btnSubmit.addActionListener(e -> {
            student.deposit(Integer.parseInt(txtAmount.getText()));
            txtBalance.setText(String.valueOf(student.getBalance()));
            lblAmount.setVisible(false);
            txtAmount.setVisible(false);
            btnSubmit.setVisible(false);
            lblAccountNumber.setVisible(false);
            lblPassword.setVisible(false);
            txtAccountNumber.setVisible(false);
            txtPassword.setVisible(false);
            btnDeposit.setEnabled(true);
        });


        balancePanel.add(lblBalance);
        balancePanel.add(txtBalance);
        balancePanel.add(lblAmount);
        balancePanel.add(txtAmount);
        balancePanel.add(btnDeposit);
        balancePanel.add(btnSubmit);
        balancePanel.add(lblAccountNumber);
        balancePanel.add(lblPassword);
        balancePanel.add(txtAccountNumber);
        balancePanel.add(txtPassword);
        balancePanel.setVisible(false);

        mainPanel.add(balancePanel);
    }

    /**
     * This method returns the meals data that we wanna' show in table.
     * @return
     */
    private Object[][] getMeals() {
        Meal[] meals = FileInterface.getMeals();
        Object[][] data = new Object[7][4];
        for (int i = 0; i < 7; i++) {
            data[i][0] = switch (i) {
                case 0 -> "Saturday";
                case 1 -> "Sunday";
                case 2 -> "Monday";
                case 3 -> "Tuesday";
                case 4 -> "Wednesday";
                case 5 -> "Thursday";
                case 6 -> "Friday";
                default -> null;
            };
            data[i][1] = meals[i].getName();
            data[i][2] = meals[i].getCost();
            JButton btnReserve = new JButton("Reserve");
            int finalI = i;
            btnReserve.addActionListener(e -> {
                if (student.reserveMeal(meals[finalI])) {
                    personalPanel.setVisible(true);
                    mealPanel.setVisible(false);
                } else {
                    Border border = BorderFactory.createLineBorder(Color.RED);
                    btnReserve.setBorder(border);
                }
            });
            data[i][3] = btnReserve;
        }
        return data;
    }

    /**
     * This method return the classrooms data that we wanna' show in table.
     * @return
     */
    private Object[][] getClassRooms() {
        List<Classroom> classrooms = new ArrayList<>();
        for (Unit unit : student.getUnits()) {
            classrooms.add(unit.getClassroom());
        }
        Object[][] data = new Object[classrooms.size()][7];
        for (int i = 0; i < classrooms.size(); i++) {
            data[i][0] = classrooms.get(i).getName();
            data[i][1] = classrooms.get(i).getNumberOfUnit();
            data[i][2] = "Mr. " + classrooms.get(i).getProfessor().getLastName();
            data[i][3] = classrooms.get(i).getClassTime();
            data[i][4] = classrooms.get(i).getClassDay();
            data[i][5] = student.getUnits().get(i).getGrade();
            JButton btnDelete = new JButton("Delete");
            data[i][6] = btnDelete;
        }
        return data;
    }

    /**
     * This method shows a new frame that student can choose a new classroom to join in.
     */
    private void addClassroom() {
        JFrame classroomsFrame = new JFrame("Classrooms");
        classroomsFrame.setBounds(200, 200, 600, 500);
        classroomsFrame.setLayout(null);

        Object[][] data = getAllClassRooms();

        String[] columnNames = {"Name", "NumberOfUnits", "Time", "Day", "Professor", "Select"};

        TableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);

        TableCellRenderer renderer = (table1, value, isSelected, hasFocus, row, column) -> (JButton) value;

        table.getColumn("Select").setCellRenderer(renderer);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                List<Classroom> studentsClassrooms = new ArrayList<>();
                for (Unit unit : student.getUnits()) {
                    studentsClassrooms.add(unit.getClassroom());
                }
                List<Classroom> allClassrooms = FileInterface.allClassrooms();
                List<Classroom> classrooms = new ArrayList<>();
                for (Classroom classroom : allClassrooms) {
                    if (!studentsClassrooms.contains(classroom))
                        classrooms.add(classroom);
                }
                Classroom classroom;
                if (column == 5 && student.addUnit(classroom = classrooms.get(row))) {
                    classroom.addStudent(student);
                    FileInterface.updateProfessor(classroom.getProfessor());
                    classroomsFrame.dispose();
                    frame.dispose();
                    new StudentPanel(student);
                }
            }
        });

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 20, 540, 430);

        classroomsFrame.add(sp);
        classroomsFrame.setVisible(true);
    }

    /**
     * This method return all of the classrooms that the student can join.
     * @return
     */
    private Object[][] getAllClassRooms() {
        List<Classroom> classrooms = FileInterface.allClassrooms();
        List<Classroom> selectedClassrooms = new ArrayList<>();
        for (Unit unit : student.getUnits()) {
            selectedClassrooms.add(unit.getClassroom());
        }
        Object[][] data = new Object[classrooms.size() - selectedClassrooms.size()][6];
        int i = 0;
        int index = 0;
        while (index < classrooms.size() && i < data.length) {
            Classroom classroom;
            if (!selectedClassrooms.contains(classroom = classrooms.get(index))) {
                data[i][0] = classroom.getName();
                data[i][1] = classroom.getNumberOfUnit();
                data[i][2] = classroom.getClassTime();
                data[i][3] = classroom.getClassDay();
                data[i][4] = "MR. " + classroom.getProfessor().getLastName();
                JButton btnSelect = new JButton("Select");
                btnSelect.addActionListener(e -> {
                    Unit unit = new Unit();
                    unit.setClassroom(classroom);
                    classroom.addStudent(student);
                });
                data[i][5] = btnSelect;
                i++;
                index++;
            } else {
                index++;
            }
        }
        return data;
    }
}
