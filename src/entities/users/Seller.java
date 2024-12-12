package entities.users;

public class Seller extends User {
    private String phoneNumber;
    private String address;

    public Seller(String name, String password, String email, String phoneNumber, String address) {
        super(name, password, email);
        this.phoneNumber = phoneNumber;
        this.address = address;
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
    public String getRole() {
        return Role.SELLER;
    }

    @Override
    public String toString() {
        return super.toString() + ", phoneNumber='" + phoneNumber + '\'' + ", address='" + address + '\'' + '}';
    }
}
