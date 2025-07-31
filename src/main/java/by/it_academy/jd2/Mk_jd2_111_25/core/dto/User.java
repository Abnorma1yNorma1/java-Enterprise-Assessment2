package by.it_academy.jd2.Mk_jd2_111_25.core.dto;

import java.time.LocalDate;

public class User {

    private String login;
    private String password;
    private String name;
    private LocalDate  birthDate;
    private LocalDate registrationDate;
    private Role role;

    private User(){}

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private final User user = new User();

        public Builder login( String login) {
            user.login = login;
            return this;
        }

        public Builder password(String password){
            user.password = password;
            return this;
        }

        public Builder name(String name){
            user.name = name;
            return this;
        }

        public Builder birthDate(LocalDate birthDate){
            user.birthDate = birthDate;
            return this;
        }

        public Builder registrationDate(LocalDate registrationDate){
            user.registrationDate = registrationDate;
            return this;
        }

        public Builder role(Role role){
            user.role = role;
            return this;
        }

        public User build(){
            return user;
        }
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
