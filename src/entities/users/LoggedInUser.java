package entities.users;

import java.util.EnumSet;
import java.util.Set;

public class LoggedInUser extends User {
    private String phoneNumber;
    private String address;
    private Set<Role> roles;

    public LoggedInUser(String name, String password, String email, String phoneNumber, String address) {
        this(name, password, email, phoneNumber, address, Role.BUYER); // Default to Role.BUYER
    }

    // Constructor with a single Role
    public LoggedInUser(String name, String password, String email, String phoneNumber, String address, Role role) {
        this(name, password, email, phoneNumber, address, EnumSet.of(role != null ? role : Role.BUYER)); // Default to Role.BUYER if null
    }

    // Constructor with multiple Roles
    public LoggedInUser(String name, String password, String email, String phoneNumber, String address, Set<Role> roles) {
        super(name, password, email);
        this.phoneNumber = phoneNumber != null ? phoneNumber : "Unknown Phone";
        this.address = address != null ? address : "Unknown Address";
        this.roles = roles != null && !roles.isEmpty() ? EnumSet.copyOf(roles) : EnumSet.of(Role.BUYER); // Default to Role.BUYER if roles are null or empty
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
