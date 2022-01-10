package dev.udd.user.infrastructure.persistence;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import dev.udd.user.domain.User;
import dev.udd.user.domain.UserEmail;
import dev.udd.user.domain.UserId;
import dev.udd.user.domain.UserRepository;
import dev.udd.user.domain.UserUsername;

@Component
public final class InMemoryUserRepository implements UserRepository {

    protected static final Map<UserId, User> USERS = new HashMap<>();

    public static void clear() {
        USERS.clear();
    }

    @Override
    public void save(User user) {

        USERS.put(user.id(), user);
    }

    @Override
    public User getById(UserId id) {

        return USERS.get(id);
    }

    @Override
    public User getByEmail(UserEmail email) {

        for (Map.Entry<UserId, User> entry : USERS.entrySet()) {
            if (USERS.get(entry.getKey()).email().equals(email)) {
                return USERS.get(entry.getKey());
            }
        }

        return null;
    }

    @Override
    public User getByUsername(UserUsername username) {

        for (Map.Entry<UserId, User> entry : USERS.entrySet()) {
            if (USERS.get(entry.getKey()).username().equals(username)) {
                return USERS.get(entry.getKey());
            }
        }
        return null;
    }

    @Override
    public void delete(User user) {

        USERS.remove(user.id());
    }

}
