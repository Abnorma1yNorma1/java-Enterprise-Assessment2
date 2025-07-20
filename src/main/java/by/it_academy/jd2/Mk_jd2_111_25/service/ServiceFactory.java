package by.it_academy.jd2.Mk_jd2_111_25.service;

import by.it_academy.jd2.Mk_jd2_111_25.service.api.IMessageService;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IUserService;
import by.it_academy.jd2.Mk_jd2_111_25.storage.StorageFactory;

public class ServiceFactory {

    private final static IUserService userService = new UserService(
            StorageFactory.getUserStorage()
    );
    private final static IMessageService messageService = new MessageService(
            StorageFactory.getMessageStorage()
    );

    private ServiceFactory() {
    }

    public static IUserService getUserService() {
        return userService;
    }

    public static IMessageService getMessageService() {
        return messageService;
    }

}
