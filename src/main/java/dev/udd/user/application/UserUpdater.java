package dev.udd.user.application;

import dev.udd.user.application.command.UserUpdateCommand;
import dev.udd.user.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
final public class UserUpdater {

    @Autowired
    private UserRepository repository;

    public void update(UserUpdateCommand command) throws UserNotFound, UserAlreadyExists, UserValueInvalid {

        UserId userId = UserId.fromString(command.getUuid());
        UserEmail email = UserEmail.fromString(command.getEmail());
        UserName name = UserName.fromString(command.getName());
        UserUsername username = UserUsername.fromString(command.getUsername());
        UserPassword password = UserPassword.fromString(command.getPassword());

        this.ensureUserExists(userId);
        this.ensureEmailIsUnique(userId, email);
        this.ensureUsernameIsUnique(userId, username);

        User user = new User(userId, name, email, username, password);

        this.repository.save(user);
    }


    private void ensureUserExists(UserId userId) throws UserNotFound {

        User user = repository.getById(userId);

        if (user == null) {
            throw new UserNotFound(userId);
        }
    }

    private void ensureEmailIsUnique(UserId userId, UserEmail email) throws UserAlreadyExists {

        User user = repository.getByEmail(email);
        if (user != null && !userId.value().equals(user.id().value())) {
            throw new UserAlreadyExists(email);
        }
    }

    private void ensureUsernameIsUnique(UserId userId, UserUsername username) throws UserAlreadyExists {

        User user = repository.getByUsername(username);
        if (user != null && !userId.equals(user.id())) {
            throw new UserAlreadyExists(username);
        }
    }

}
