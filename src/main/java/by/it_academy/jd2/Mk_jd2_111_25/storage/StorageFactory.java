package by.it_academy.jd2.Mk_jd2_111_25.storage;

import by.it_academy.jd2.Mk_jd2_111_25.storage.api.StorageException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class StorageFactory {
    private final static DataSource dataSource;

    static {
        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass("org.postgresql.Driver");
            cpds.setJdbcUrl("jdbc:postgresql://localhost/chat_app");
            cpds.setUser("postgres");
            cpds.setPassword("postgres");

            dataSource = cpds;
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    private final static UserStorageSQL userStorage;
    private final static MessageStorageSQL messageStorage;

    static {
        userStorage = new UserStorageSQL(dataSource);
        messageStorage = new MessageStorageSQL(dataSource);
    }

    private StorageFactory(){}

    public static UserStorageSQL getUserStorage(){return userStorage;}
    public static MessageStorageSQL getMessageStorage(){return messageStorage;}
}
