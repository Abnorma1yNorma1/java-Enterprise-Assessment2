package by.it_academy.jd2.Mk_jd2_111_25.service;

import by.it_academy.jd2.Mk_jd2_111_25.dto.Message;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IMessageService;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.IMessageStorage;

import java.util.List;

public class MessageService implements IMessageService {

    private final IMessageStorage storage;

    public MessageService(IMessageStorage storage) {
        this.storage = storage;
    }

    @Override
    public List<Message> getMessages(String login) {
        return storage.getMessages(login);
    }

    @Override
    public void send(Message message) {
        storage.add(message);
    }

    @Override
    public int count() {
        return storage.count();
    }
}
