import java.io.*;
import java.util.ArrayList;
import java.util.List;

public interface FileInterface {

    File students = new File("src/file/Students.dat");
    File admins = new File("src/file/Admins.dat");
    File mealPlan = new File("src/file/MealPlan.dat");
    File professors = new File("src/file/Professors.dat");
    File updater = new File("src/file/Updater.dat");

    static List<Student> allStudents() {
        List<Student> list = new ArrayList<>();
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(students))) {
            while (true) {
                Student std = (Student) reader.readObject();
                list.add(std);
            }
        } catch (ClassNotFoundException | IOException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        }
        return list;
    }

    static List<Professor> allProfessor() {
        List<Professor> list = new ArrayList<>();
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(professors))) {
            while (true) {
                Professor professor = (Professor) reader.readObject();
                list.add(professor);
            }
        } catch (ClassNotFoundException | IOException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        }
        return list;
    }


    static Student getStudent(String id) {
        Student student = null;
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(students))) {
            while (true) {
                student = (Student) reader.readObject();
                if (student.getId().equals(id))
                    return student;
            }
        } catch (IOException | ClassNotFoundException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        }
        return null;
    }


    static Professor getProfessor(String id) {


    }


    static Admin getAdmin(String id) {

    }

    static boolean exists(String id, Person person) {
    }


    static boolean addProfessor(Professor professor) {

    }

    static void updateStudent(Student st) {

    }

    static void updateProfessor(Professor prof) {


    }

    static void updateAdmin(Admin admin) {
    }

    static void updateStudentsId(String oldId, String newId) {

    }

    static void updateProfessorsId(String oldId, String newId) {


    }

    static void updateAdminId(String oldId, String newId) {

    }

    static List<Classroom> allClassrooms () {

    }

    static void writeMeals() {

    }

    static Meal[] getMeals() {


    }

    private static void checkAndClose (ObjectInputStream reader, ObjectOutputStream writer){



    }
}
