package by.it_academy.jd2.Mk_jd2_111_25.storage;

import by.it_academy.jd2.Mk_jd2_111_25.storage.api.IUserStorage;

import javax.sql.DataSource;

public class UserStorageSQL implements IUserStorage {
    private final DataSource dataSource;

    public UserStorageSQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
