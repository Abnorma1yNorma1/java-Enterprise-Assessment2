package by.it_academy.jd2.Mk_jd2_111_25.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.core.dto.Message;

import java.util.List;

public interface IMessageService {
    List<Message> getMessages(String login);
    void send(Message message);
    int count();
}
