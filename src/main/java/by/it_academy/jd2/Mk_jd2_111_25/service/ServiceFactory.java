package by.it_academy.jd2.Mk_jd2_111_25.service;

import by.it_academy.jd2.Mk_jd2_111_25.dto.Message;
import by.it_academy.jd2.Mk_jd2_111_25.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IMessageService;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IUserService;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IValidator;
import by.it_academy.jd2.Mk_jd2_111_25.storage.StorageFactory;

public class ServiceFactory {

    private final static IValidator<User> userValidator = new UserValidator();
    private final static IValidator<Message> messageValidator = new MessageValidator();

    private final static IUserService userService = new UserService(
            StorageFactory.getUserStorage(),
            userValidator

    );
    private final static IMessageService messageService = new MessageService(
            StorageFactory.getMessageStorage(),
            messageValidator
    );

    private ServiceFactory() {
    }

    public static IUserService getUserService() {
        return userService;
    }

    public static IMessageService getMessageService() {
        return messageService;
    }

    public static IValidator<User> getUserValidator(){return userValidator;}

}
