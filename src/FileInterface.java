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
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(FileInterface.professors));) {
            while (true) {
                Professor professor = (Professor) reader.readObject();
                if (professor.getId().equals(id))
                    return professor;
            }
        } catch (IOException | ClassNotFoundException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        }
        return null;
    }


    static Admin getAdmin(String id) {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(admins))) {
            while (true) {
                Admin admin = (Admin) reader.readObject();
                if (admin.getId().equals(id))
                    return admin;
            }
        } catch (ClassNotFoundException | IOException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        }
        return null;
    }

    static boolean exists(String id, Person person) {
        boolean exist = false;
        if (person instanceof Student) {
            if (getStudent(id) != null)
                exist = true;
        }
        if (person instanceof Professor) {
            if (getProfessor(id) != null)
                exist = true;
        }
        return exist;
    }

    static boolean addStudent(Student st) {
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
        boolean flag = false;

        try {
            reader = new ObjectInputStream(new FileInputStream(students));
            writer = new ObjectOutputStream(new FileOutputStream(updater));
            while (true) {
                writer.writeObject(reader.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        } finally {
            checkAndClose(reader, writer);
        }
        try {
            writer = new ObjectOutputStream(new FileOutputStream(students));
            reader = new ObjectInputStream(new FileInputStream(updater));
            while (true) {
                writer.writeObject(reader.readObject());
            }
        } catch (EOFException e) {
            try {
                writer.writeObject(st);
                flag = true;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        } finally {
            checkAndClose(reader, writer);
        }
        return flag;
    }

    static boolean addProfessor(Professor professor) {
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
        boolean flag = false;

        try {
            reader = new ObjectInputStream(new FileInputStream(professors));
            writer = new ObjectOutputStream(new FileOutputStream(updater));
            while (true) {
                writer.writeObject(reader.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        } finally {
            checkAndClose(reader, writer);
        }
        try {
            writer = new ObjectOutputStream(new FileOutputStream(professors));
            reader = new ObjectInputStream(new FileInputStream(updater));
            while (true) {
                writer.writeObject(reader.readObject());
            }
        } catch (EOFException e) {
            try {
                writer.writeObject(professor);
                flag = true;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            checkAndClose(reader, writer);

        }
        return flag;
    }


    static void updateStudent(Student st) {
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(students));
            writer = new ObjectOutputStream(new FileOutputStream(updater));
            while (true) {
                writer.writeObject(reader.readObject());
            }
        } catch (ClassNotFoundException | IOException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        } finally {
            checkAndClose(reader, writer);
        }
        try {
            writer = new ObjectOutputStream(new FileOutputStream(students));
            reader = new ObjectInputStream(new FileInputStream(updater));
            while (true) {
                Student student = (Student) reader.readObject();
                if (student.equals(st)) {
                    student = st;
                }
                writer.writeObject(student);
            }
        } catch (ClassNotFoundException | IOException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        } finally {
            checkAndClose(reader, writer);
        }
    }


    static void updateProfessor(Professor prof) {
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;

        try {
            reader = new ObjectInputStream(new FileInputStream(professors));
            writer = new ObjectOutputStream(new FileOutputStream(updater));
            while (true) {
                writer.writeObject(reader.readObject());
            }
        } catch (ClassNotFoundException | IOException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        } finally {
            checkAndClose(reader, writer);
        }

        try {
            writer = new ObjectOutputStream(new FileOutputStream(professors));
            reader = new ObjectInputStream(new FileInputStream(updater));

            while (true) {
                Professor professor = (Professor) reader.readObject();
                if (professor.equals(prof)) {
                    professor = prof;
                }
                writer.writeObject(professor);
            }
        } catch (ClassNotFoundException | IOException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        } finally {
            checkAndClose(reader, writer);
        }
    }

    static void updateAdmin(Admin admin) {
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;

        try {
            writer = new ObjectOutputStream(new FileOutputStream(admins));
            reader = new ObjectInputStream(new FileInputStream(updater));

            while (true) {
                Admin currentAdmin = (Admin) reader.readObject();
                if (currentAdmin.equals(admin)) {
                    currentAdmin = admin;
                }

                writer.writeObject(currentAdmin);
            }
        } catch (ClassNotFoundException | IOException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        } finally {
            checkAndClose(reader, writer);
        }
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
