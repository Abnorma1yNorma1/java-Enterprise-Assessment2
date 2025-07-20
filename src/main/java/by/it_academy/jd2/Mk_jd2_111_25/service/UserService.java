package by.it_academy.jd2.Mk_jd2_111_25.service;

import by.it_academy.jd2.Mk_jd2_111_25.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IUserService;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.IUserStorage;

public class UserService implements IUserService {

    private final IUserStorage storage;

    public UserService(IUserStorage storage) {
        this.storage = storage;
    }


}
