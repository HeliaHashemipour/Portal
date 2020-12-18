import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * This class is the super class of every role in this application.
 */
public abstract class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 4605491601859615470L;

    private String firstName;
    private String lastName;
    private String id;
    private String password;

    /**
     * The getter method of firstname.
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * The setter method of firstname.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * the getter method of lastname.
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * the setter method of lastname.
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * the getter method of id.
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * the setter method of id.
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The getter method of password.
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * The setter method of password.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * checks if two persons are equal or not by their id (username).
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId());
    }

    /**
     * returns hashcode of this object.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
