package Models;

public class User {

    String id;
    String email;
    String role;
    String password;

    public User(String id, String email,String role, String password) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getID() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                "email='" + email + '\'' +
                "role='" + role + '\'' +
                '}';
    }
}
