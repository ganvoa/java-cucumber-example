package dev.udd.user.application;


import dev.udd.user.application.command.UserDeleteCommand;
import dev.udd.user.application.query.UserFindQuery;
import dev.udd.user.application.response.UserResponse;
import dev.udd.user.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final public class UserDeleter {

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
