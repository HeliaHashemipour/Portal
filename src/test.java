import java.io.*;
public class test {
    public static void main(String[] args) throws IOException {

        File file = new File("src/file/Students.dat");
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
        Student student = new Student();
        student.setFirstName("Helia");
        student.setLastName("Hashemipour");
        student.setId("9831106");
        student.setPassword("12345");
        writer.writeObject(student);
        System.out.println("Done");
    }
}
