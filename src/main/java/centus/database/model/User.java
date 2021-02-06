package centus.database.model;

import centus.utils.users.PasswordAuthentication;
import com.j256.ormlite.field.DatabaseField;

public class User extends BaseModel{
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME")
    private String name;

    @DatabaseField(columnName = "PASSWORD")
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        PasswordAuthentication pa = new PasswordAuthentication();
        String hash = pa.hash(password);
        this.password = hash;
    }
}
