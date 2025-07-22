package by.it_academy.jd2.Mk_jd2_111_25.storage;

import by.it_academy.jd2.Mk_jd2_111_25.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.IUserStorage;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.StorageException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStorageSQL implements IUserStorage {
    private final DataSource dataSource;

    public UserStorageSQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user){
        try (Connection conn = dataSource.getConnection();
             PreparedStatement select = conn.prepareStatement("SELECT login FROM chat_app.users WHERE login = ?")
        ) {
            select.setString(1, user.getLogin());
            try (ResultSet result = select.executeQuery()){
                if (result.next()) {
                    throw new StorageException("user already exists");
                } else {
                    java.sql.Date sqlDate = java.sql.Date.valueOf(user.getBirthDate());
                    try (PreparedStatement insert = conn.prepareStatement("INSERT INTO chat_app.users(login, password, name, birth_date, role)" +
                            "VALUES(?,?,?,?,?)")) {
                        insert.setString(1, user.getLogin());
                        insert.setString(2, user.getPassword());
                        insert.setString(3, user.getName());
                        insert.setDate(4, sqlDate);
                        insert.setString(5, user.getRole().toString());
                        insert.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new StorageException("Failed to add user", e);
        }
    }

    @Override
    public boolean userExists(String login) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement select = conn.prepareStatement("SELECT login FROM chat_app.users WHERE login = ?")
        ) {
            select.setString(1, login);
            try (ResultSet result = select.executeQuery()){
                return result.next();
            }
        } catch (SQLException e) {
            throw new StorageException("Failed to check user existence", e);
        }
    }

    @Override
    public boolean validPassword(String login, String password) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement select = conn.prepareStatement("SELECT login FROM chat_app.users WHERE login = ? AND password = ?")
        ) {
            select.setString(1, login);
            select.setString(2, password);
            try(ResultSet result = select.executeQuery()){
                return result.next();
            }
        } catch (SQLException e) {
            throw new StorageException("Failed to validate user password", e);
        }
    }

    @Override
    public int count() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement select = conn.prepareStatement("SELECT COUNT(*) AS total_users FROM chat_app.users");
             ResultSet result = select.executeQuery()
        ) {
            if (result.next()) {
                return result.getInt("total_users");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new StorageException("Failed to count users", e);
        }
    }
}
