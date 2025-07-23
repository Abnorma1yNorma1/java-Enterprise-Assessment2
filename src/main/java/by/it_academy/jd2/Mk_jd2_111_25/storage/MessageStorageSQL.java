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
             PreparedStatement select = conn.prepareStatement("SELECT id, fromWho, toWhom, content, sent_time FROM chat_app.messages WHERE toWhom  = ? ORDER BY sent_time ASC")
        ) {
            select.setString(1, login);
            try (ResultSet result = select.executeQuery()){
                while(result.next()){
                    Message m = new Message();
                    m.setText(result.getString("content"));
                    m.setFromWho(result.getString("fromWho"));
                    m.setDate(result.getTimestamp("sent_time"));
                    list.add(m);
                }
            }
        } catch (SQLException e) {
            throw new StorageException("Failed to fetch messages for user: " + login, e);
        }
        return list;
    }

    @Override
    public void add(Message message) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement insert = conn.prepareStatement("INSERT INTO chat_app.messages(fromWho, toWhom, content)" +
                     "VALUES(?,?,?)")){
            insert.setString(1, message.getFromWho());
            insert.setString(2, message.getToWhom());
            insert.setString(3, message.getText());
            insert.executeUpdate();
        }catch (SQLException e){
            throw new StorageException("Failed to add message", e);
        }
    }

    @Override
    public int count() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement select = conn.prepareStatement("SELECT COUNT(*) AS total_messages FROM chat_app.messages");
             ResultSet result = select.executeQuery()
        ) {
            if (result.next()) {
                return result.getInt("total_messages");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new StorageException("Failed to count messages", e);
        }
    }

}
