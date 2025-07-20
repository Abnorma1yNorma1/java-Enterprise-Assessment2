package by.it_academy.jd2.Mk_jd2_111_25.storage;

import by.it_academy.jd2.Mk_jd2_111_25.dto.Message;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.IMessageStorage;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.StorageException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageStorageSQL implements IMessageStorage {
    private final DataSource dataSource;

    public MessageStorageSQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Message> getMessages(String login) {
        ArrayList<Message> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement select = conn.prepareStatement("SELECT id, fromWho, toWhom, content, sent_time FROM chat_app.messages WHERE toWhom  = ?")
        ) {
            select.setString(1, login);
            try (ResultSet result = select.executeQuery()){
                while(result.next()){
                    Message m = new Message();
                    m.setText(result.getString("content"));
                    m.setFromWho(result.getString("fromWho"));
                    m.setDate(result.getTimestamp("sent_time").toLocalDateTime());
                    list.add(m);
                }
            }
        } catch (SQLException e) {
            throw new StorageException("Failed to to fetch messages for user: " + login, e);
        }
        return list;
    }
}
