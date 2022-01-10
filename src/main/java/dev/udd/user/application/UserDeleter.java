package dev.udd.user.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.udd.user.application.command.UserDeleteCommand;
import dev.udd.user.domain.User;
import dev.udd.user.domain.UserId;
import dev.udd.user.domain.UserNotFound;
import dev.udd.user.domain.UserRepository;
import dev.udd.user.domain.UserValueInvalid;

@Service
public final class UserDeleter {

    @Autowired
    public UserRepository repository;

    public void delete(UserDeleteCommand command) throws UserNotFound, UserValueInvalid {

        UserId userId = UserId.fromString(command.getUuid());
        User user = repository.getById(userId);

        if (user == null) {

            throw new UserNotFound(userId);
        }

        this.repository.delete(user);
    }

}
