package by.it_academy.jd2.Mk_jd2_111_25.core.dto;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AppStatistics {
    private final Set<String> activeUsers = ConcurrentHashMap.newKeySet();
    private final AtomicInteger totalUsers = new AtomicInteger(0);
    private final AtomicInteger totalMessages = new AtomicInteger(0);

    public void userLoggedIn(String login) {
        activeUsers.add(login);
    }

    public void userLoggedOut(String login) {
        activeUsers.remove(login);
    }

    public int getActiveUsersCount() {
        return activeUsers.size();
    }

    public void setTotalUsers(int count) {
        totalUsers.set(count);
    }

    public int getTotalUsers() {
        return totalUsers.get();
    }

    public void incrementMessages() {
        totalMessages.incrementAndGet();
    }

    public void setTotalMessages(int count) {
        totalMessages.set(count);
    }

    public int getTotalMessages() {
        return totalMessages.get();
    }
}

