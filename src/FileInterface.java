import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface contacts with the files and change 'em.
 */
public interface FileInterface {

    File students = new File("src/file/Students.dat");
    File admins = new File("src/file/Admins.dat");
    File mealPlan = new File("src/file/MealPlan.dat");
    File professors = new File("src/file/Professors.dat");
    File updater = new File("src/file/Updater.dat");

    /**
     * This method returns all of the students.
     * @return
     */
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

    /**
     * this method returns all of the professors.
     * @return
     */
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

    /**
     * This method gets an id and return student with the given id or null if there is no such student with given id.
     * @param id
     * @return
     */
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

    /**
     * This method gets an id and returns the professor with the given id or null if there is no such professor with given id.
     * @param id
     * @return
     */
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

    /**
     * This method gets an id and return admin with the given id or null if there is no such admin.
     * @param id
     * @return
     */
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

    /**
     * This method gets an id and a person and checks files and return true if the id exists in files.
     * @param id
     * @param person
     * @return
     */
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

    /**
     * This method gets a student and adds it to Students file.
     * @param st
     * @return
     */
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

    /**
     * This method gets an professor and adds it to the professors file.
     * @param professor
     * @return
     */
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

    /**
     * This method gets an student and update its info inside the file
     * @param st
     */
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

    /**
     * This method gets an professor and updates its info inside the file.
     * @param prof
     */
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

    /**
     * This method gets an admin and updates its info inside the file.
     * @param admin
     */
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

    /**
     * This method gets an old id and a new id and changes the id of the given student if exists.
     * @param oldId
     * @param newId
     */
    static void updateStudentsId(String oldId, String newId) {
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
                if (student.getId().equals(oldId)) {
                    student.setId(newId);
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

    /**
     * This method gets an old id and a new id and changes the id of the given professor in file.
     * @param oldId
     * @param newId
     */
    static void updateProfessorsId(String oldId, String newId) {
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
                if (professor.getId().equals(oldId)) {
                    professor.setId(newId);
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

    /**
     * This method gets an old id and a new id and changes id of the given admin inside file.
     * @param oldId
     * @param newId
     */
    static void updateAdminId(String oldId, String newId) {
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
        try {
            reader = new ObjectInputStream(new FileInputStream(admins));
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
            writer = new ObjectOutputStream(new FileOutputStream(admins));
            reader = new ObjectInputStream(new FileInputStream(updater));
            while (true) {
                Admin admin = (Admin) reader.readObject();
                if (admin.getId().equals(oldId)) {
                    admin.setId(newId);
                }

                writer.writeObject(admin);
            }
        } catch (ClassNotFoundException | IOException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        } finally {
            checkAndClose(reader, writer);
        }
    }

    /**
     * This method return all of the classrooms
     * @return
     */
    static List<Classroom> allClassrooms () {
        List<Classroom> list = new ArrayList<>();
        for (int i = 0; i < allProfessor().size(); i++) {
            list.addAll(allProfessor().get(i).getClassrooms());
        }
        return list;
    }

    /**
     * Thiw method writes the meal plan inside of MealPlan.dat
     */
    static void writeMeals() {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(mealPlan))){
            writer.writeObject(MealPlan.getMeals());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method gets meal plan from files
     * @return
     */
    static Meal[] getMeals() {
        Meal[] meals = new Meal[7];
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(mealPlan))){
            meals = (Meal[]) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            if (!(e instanceof EOFException))
                e.printStackTrace();
        }
        MealPlan.setMeals(meals);
        return meals;
    }

    /**
     * This method checks and closes all of the connections with files.
     * @param reader
     * @param writer
     */
    private static void checkAndClose (ObjectInputStream reader, ObjectOutputStream writer){
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        }

        if (writer != null) {
            try {
                writer.close();
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }
    }
}
