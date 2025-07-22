package by.it_academy.jd2.Mk_jd2_111_25.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.dto.Role;

import java.time.LocalDate;

public interface IUserService {

    void addUser(
            String login,
            String password,
            String name,
            LocalDate date,
            Role role);

    boolean authenticate(String login, String password);
    int count();
}
