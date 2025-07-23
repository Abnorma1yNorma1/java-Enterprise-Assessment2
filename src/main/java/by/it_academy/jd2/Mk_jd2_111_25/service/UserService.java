package by.it_academy.jd2.Mk_jd2_111_25.service;

import by.it_academy.jd2.Mk_jd2_111_25.dto.Role;
import by.it_academy.jd2.Mk_jd2_111_25.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IUserService;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.IUserStorage;

import java.time.LocalDate;

public class UserService implements IUserService {

    private final IUserStorage storage;

    public UserService(IUserStorage storage) {
        this.storage = storage;
    }

    @Override
    public void addUser(String login, String password, String name, LocalDate date, Role role) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setBirthDate(date);
        user.setRole(role);
        storage.add(user);
    }

    @Override
    public boolean authenticate(String login, String password) {
        return storage.userExists(login) && storage.validPassword(login, password);
    }

    @Override
    public int count() {
        int count = storage.count();
        return count;
    }

    @Override
    public Role getRole(String login) {
        return storage.getRole(login);
    }
}
