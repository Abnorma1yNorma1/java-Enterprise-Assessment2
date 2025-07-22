package by.it_academy.jd2.Mk_jd2_111_25.storage.api;

import by.it_academy.jd2.Mk_jd2_111_25.dto.User;

public interface IUserStorage {

    void add(User user );

    boolean userExists(String login);
    boolean validPassword (String login, String password);
    int count();
}
