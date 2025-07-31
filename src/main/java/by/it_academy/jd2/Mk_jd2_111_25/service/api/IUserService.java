package by.it_academy.jd2.Mk_jd2_111_25.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.dto.Role;
import by.it_academy.jd2.Mk_jd2_111_25.dto.User;

import java.time.LocalDate;

public interface IUserService {

    void create(User user);
    boolean authenticate(String login, String password);
    int count();
    Role getRole(String login);
}
