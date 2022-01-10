package dev.udd.user.infrastructure.persistence;

import java.util.HashMap;
import org.springframework.stereotype.Component;
import dev.udd.user.domain.User;
import dev.udd.user.domain.UserEmail;
import dev.udd.user.domain.UserId;
import dev.udd.user.domain.UserRepository;
import dev.udd.user.domain.UserUsername;

@Component
public final class InMemoryUserRepository implements UserRepository {

    public static final HashMap<UserId, User> USERS = new HashMap<>();

    @Override
    public void save(User user) {

        USERS.put(user.id(), user);
    }

    @Override
    public User getById(UserId id) {

        User user = USERS.get(id);
        return user;
    }

    @Override
    public User getByEmail(UserEmail email) {

        for (UserId userId : USERS.keySet()) {
            if (USERS.get(userId).email().equals(email)) {
                return USERS.get(userId);
            }
        }

        return null;
    }

    @Override
    public User getByUsername(UserUsername username) {

        for (UserId userId : USERS.keySet()) {
            if (USERS.get(userId).username().equals(username)) {
                return USERS.get(userId);
            }
        }
        return null;
    }

    @Override
    public void delete(User user) {

        USERS.remove(user.id());
    }

}
