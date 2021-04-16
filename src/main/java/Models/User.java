package Models;

public class User {

    String id;
    String email;
    String role;

    public User(String id, String email,String role) {
        this.id = id;
        this.email = email;
        this.role = role;
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

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                "email='" + email + '\'' +
                "role='" + role + '\'' +
                '}';
    }
}
