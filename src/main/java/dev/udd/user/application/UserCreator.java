package dev.udd.user.application;

import dev.udd.user.application.command.UserCreateCommand;
import dev.udd.user.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
final public class UserCreator {

    @Autowired
    private UserRepository repository;

    public void create(UserCreateCommand command) throws UserAlreadyExists, UserValueInvalid {

        UserId userId = UserId.fromString(command.getUuid());
        UserEmail email = UserEmail.fromString(command.getEmail());
        UserName name = UserName.fromString(command.getName());
        UserUsername username = UserUsername.fromString(command.getUsername());
        UserPassword password = UserPassword.fromString(command.getPassword());

        this.ensureIdIsUnique(userId);
        this.ensureEmailIsUnique(email);
        this.ensureUsernameIsUnique(username);

        User user = new User(userId, name, email, username, password);

        this.repository.save(user);
    }

    private void ensureIdIsUnique(UserId userId) throws UserAlreadyExists {

        User user = repository.getById(userId);
        if (user != null) {
            throw new UserAlreadyExists(userId);
        }
    }

    private void ensureEmailIsUnique(UserEmail email) throws UserAlreadyExists {

        User user = repository.getByEmail(email);
        if (user != null) {
            throw new UserAlreadyExists(email);
        }
    }

    private void ensureUsernameIsUnique(UserUsername username) throws UserAlreadyExists {

        User user = repository.getByUsername(username);
        if (user != null) {
            throw new UserAlreadyExists(username);
        }
    }

}
