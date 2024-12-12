package entities.users;

import entities.users.Role;

import java.util.Set;

public abstract class User {
    private String name;
    private String password;
    private String email;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
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
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract Set<Role> getRoles();

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "', roles=" + getRoles() + "}";
    }
}
