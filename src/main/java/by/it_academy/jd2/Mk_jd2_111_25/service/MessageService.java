package by.it_academy.jd2.Mk_jd2_111_25.service;

import by.it_academy.jd2.Mk_jd2_111_25.service.api.IMessageService;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.IMessageStorage;

public class MessageService implements IMessageService {

    private final IMessageStorage storage;

    public MessageService(IMessageStorage storage) {
        this.storage = storage;
    }
}
