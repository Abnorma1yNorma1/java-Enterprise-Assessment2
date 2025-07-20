package by.it_academy.jd2.Mk_jd2_111_25.storage;

import by.it_academy.jd2.Mk_jd2_111_25.storage.api.IMessageStorage;

import javax.sql.DataSource;

public class MessageStorageSQL implements IMessageStorage {
    private final DataSource dataSource;

    public MessageStorageSQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
