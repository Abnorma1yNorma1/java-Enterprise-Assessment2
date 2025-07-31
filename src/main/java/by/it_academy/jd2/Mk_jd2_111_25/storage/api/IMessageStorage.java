package by.it_academy.jd2.Mk_jd2_111_25.storage.api;

import by.it_academy.jd2.Mk_jd2_111_25.core.dto.Message;

import java.util.List;

public interface IMessageStorage {
    List<Message> getMessages(String login);
    void add(Message message);
    int count();
}
