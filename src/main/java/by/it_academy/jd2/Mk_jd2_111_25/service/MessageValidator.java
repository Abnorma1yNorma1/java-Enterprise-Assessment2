package by.it_academy.jd2.Mk_jd2_111_25.service;

import by.it_academy.jd2.Mk_jd2_111_25.dto.Message;
import by.it_academy.jd2.Mk_jd2_111_25.dto.ValidationResult;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IValidator;

public class MessageValidator implements IValidator<Message> {
    @Override
    public ValidationResult validate(Message message) {
        if(message == null) {
            return ValidationResult.error("Сообщение не задано");
        }
        if(message.getToWhom() == null || message.getToWhom().isBlank()) {
            return ValidationResult.error("Получатель обязателен");
        }
        if(message.getFromWho() == null || message.getFromWho().isBlank()) {
            return ValidationResult.error("Отправитель обязателен");
        }
        if(message.getText() == null || message.getText().isBlank()) {
            return ValidationResult.error("Текст сообщения обязателен");
        }

        return ValidationResult.ok();
    }

}
