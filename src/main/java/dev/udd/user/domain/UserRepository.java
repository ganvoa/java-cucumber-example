package dev.udd.user.domain;

public interface UserRepository {

    void save(User user);

    User getById(UserId id);

    User getByEmail(UserEmail email);

    User getByUsername(UserUsername username);

    void delete(User user);

}
