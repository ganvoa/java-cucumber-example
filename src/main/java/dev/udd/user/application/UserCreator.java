package dev.udd.user.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dev.udd.user.application.command.UserUpdateOrCreateCommand;
import dev.udd.user.domain.User;
import dev.udd.user.domain.UserAlreadyExists;
import dev.udd.user.domain.UserEmail;
import dev.udd.user.domain.UserId;
import dev.udd.user.domain.UserName;
import dev.udd.user.domain.UserPassword;
import dev.udd.user.domain.UserRepository;
import dev.udd.user.domain.UserUsername;
import dev.udd.user.domain.UserValueInvalid;

@Component
public final class UserCreator {

    @Autowired
    private UserRepository repository;

    public void create(UserUpdateOrCreateCommand command)
            throws UserAlreadyExists, UserValueInvalid {

        var userId = UserId.fromString(command.getUuid());
        var email = UserEmail.fromString(command.getEmail());
        var name = UserName.fromString(command.getName());
        var username = UserUsername.fromString(command.getUsername());
        var password = UserPassword.fromString(command.getPassword());

        this.ensureIdIsUnique(userId);
        this.ensureEmailIsUnique(email);
        this.ensureUsernameIsUnique(username);

        var user = new User(userId, name, email, username, password);

        this.repository.save(user);
    }

    private void ensureIdIsUnique(UserId userId) throws UserAlreadyExists {

        var user = repository.getById(userId);
        if (user != null) {
            throw new UserAlreadyExists(userId);
        }
    }

    private void ensureEmailIsUnique(UserEmail email) throws UserAlreadyExists {

        var user = repository.getByEmail(email);
        if (user != null) {
            throw new UserAlreadyExists(email);
        }
    }

    private void ensureUsernameIsUnique(UserUsername username) throws UserAlreadyExists {

        var user = repository.getByUsername(username);
        if (user != null) {
            throw new UserAlreadyExists(username);
        }
    }

}
