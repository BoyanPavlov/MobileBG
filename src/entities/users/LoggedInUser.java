package entities.users;

import java.util.EnumSet;
import java.util.Set;

public class LoggedInUser extends User {
    private String phoneNumber;
    private String address;
    private Set<Role> roles;

    public LoggedInUser(String name, String password, String email, String phoneNumber, String address, Set<Role> roles) {
        super(name, password, email);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roles = EnumSet.copyOf(roles); // Ensures roles are unique
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    @Override
    public String toString() {
        return super.toString() + ", phoneNumber='" + phoneNumber + "', address='" + address + "'}";
    }
}
