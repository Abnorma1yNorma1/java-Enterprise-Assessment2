package by.it_academy.jd2.Mk_jd2_111_25.service;

import by.it_academy.jd2.Mk_jd2_111_25.core.dto.Message;
import by.it_academy.jd2.Mk_jd2_111_25.core.dto.ValidationResult;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IMessageService;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IValidator;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.IMessageStorage;

import java.util.List;

public class MessageService implements IMessageService {

    private final IMessageStorage storage;
    private final IValidator<Message> validator;

    public MessageService(IMessageStorage storage, IValidator<Message> validator) {
        this.storage = storage;
        this.validator = validator;
    }

    @Override
    public List<Message> getMessages(String login) {
        return storage.getMessages(login);
    }

    @Override
    public void send(Message message) {
        ValidationResult validationResult = validator.validate(message);
        if (!validationResult.isValid()){
            throw new IllegalArgumentException(validationResult.getMessage());
        }
        storage.add(message);
    }

    @Override
    public int count() {
        return storage.count();
    }
}
