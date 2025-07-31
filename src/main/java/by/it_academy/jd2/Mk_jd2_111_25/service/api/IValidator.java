package by.it_academy.jd2.Mk_jd2_111_25.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.core.dto.ValidationResult;

public interface IValidator<T> {
    ValidationResult validate(T data);
}

