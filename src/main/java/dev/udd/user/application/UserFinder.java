package dev.udd.user.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.udd.user.application.query.UserFindQuery;
import dev.udd.user.application.response.UserResponse;
import dev.udd.user.domain.User;
import dev.udd.user.domain.UserId;
import dev.udd.user.domain.UserNotFound;
import dev.udd.user.domain.UserRepository;
import dev.udd.user.domain.UserValueInvalid;

@Service
public final class UserFinder {

    @Autowired
    public UserRepository repository;

    @Autowired
    public UserMapper mapper;

    public UserResponse find(UserFindQuery query) throws UserNotFound, UserValueInvalid {

        UserId userId = UserId.fromString(query.getUuid());

        User user = ensureUserExists(userId);

        return this.mapper.response(user);
    }

    private User ensureUserExists(UserId userId) throws UserNotFound {

        User user = repository.getById(userId);

        if (user == null) {
            throw new UserNotFound(userId);
        }

        return user;
    }

}
