package dev.udd.user.infrastructure.persistence;

import dev.udd.user.domain.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
final public class InMemoryUserRepository implements UserRepository {

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

}
