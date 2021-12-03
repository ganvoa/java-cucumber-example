package dev.udd.user.application;


import dev.udd.user.application.query.UserFindQuery;
import dev.udd.user.application.response.UserResponse;
import dev.udd.user.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final public class UserFinder {

    @Autowired
    public UserRepository repository;

    @Autowired
    public UserMapper mapper;


    public UserResponse find(UserFindQuery query) throws UserNotFound, UserValueInvalid {

        UserId userId = UserId.fromString(query.getUuid());
        User user = repository.getById(userId);

        if (user == null) {
            throw new UserNotFound(userId);
        }

        return this.mapper.response(user);
    }

}
