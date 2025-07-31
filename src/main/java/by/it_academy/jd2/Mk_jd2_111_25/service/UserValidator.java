package by.it_academy.jd2.Mk_jd2_111_25.service;

import by.it_academy.jd2.Mk_jd2_111_25.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.core.dto.ValidationResult;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IValidator;

import java.time.LocalDate;

public class UserValidator implements IValidator<User> {

    @Override
    public ValidationResult validate(User user) {
        if (user == null) {
            return ValidationResult.error("Пользователь не задан");
        }
        if (user.getLogin() == null || user.getLogin().isBlank()) {
            return ValidationResult.error("Логин обязателен");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return ValidationResult.error("Пароль обязателен");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            return ValidationResult.error("Имя обязательно");
        }
        if (user.getBirthDate() == null || user.getBirthDate().isAfter(LocalDate.now())) {
            return ValidationResult.error("Неверная дата рождения");
        }
        return ValidationResult.ok();
    }
}

