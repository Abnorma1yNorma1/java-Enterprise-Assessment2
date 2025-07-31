package by.it_academy.jd2.Mk_jd2_111_25.service;

import by.it_academy.jd2.Mk_jd2_111_25.core.dto.Role;
import by.it_academy.jd2.Mk_jd2_111_25.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.core.dto.ValidationResult;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IUserService;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IValidator;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.IUserStorage;

import java.time.LocalDate;

public class UserService implements IUserService {

    private final IUserStorage storage;
    private final IValidator<User> validator;

    public UserService(IUserStorage storage, IValidator<User> validator) {
        this.storage = storage;
        this.validator = validator;
    }

    @Override
    public void create(User user) {

        ValidationResult validationResult = validator.validate(user);

        if (!validationResult.isValid()){
            throw new IllegalArgumentException(validationResult.getMessage());
        }
        if (storage.userExists(user.getLogin())) {
            throw new IllegalArgumentException("Пользователь уже существует");
        }

        user.setRole(Role.USER);
        user.setRegistrationDate(LocalDate.now());
        storage.add(user);
    }

    @Override
    public boolean authenticate(String login, String password) {
        return storage.userExists(login) && storage.validPassword(login, password);
    }

    @Override
    public int count() {
        return storage.count();
    }

    @Override
    public Role getRole(String login) {
        return storage.getRole(login);
    }
}
