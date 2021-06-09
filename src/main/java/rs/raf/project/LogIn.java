package rs.raf.project;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LogIn {


    @NotNull(message = "Email is requiered!")
    @NotEmpty(message = "Email is requiered!")
    private String email;
    @NotNull(message = "Password is requierd!")
    @NotEmpty(message = "Password is requierd!")
    private String password;

    public LogIn(){

    }

    public LogIn(@NotNull(message = "Email is requiered!") @NotEmpty(message = "Email is requiered!") String email, @NotNull(message = "Password is requierd!") @NotEmpty(message = "Password is requierd!") String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
