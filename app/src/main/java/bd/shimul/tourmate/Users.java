package bd.shimul.tourmate;

public class Users {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String registrationDate;

    public Users(String firstName, String lastName, String email, String password, String registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }
}
