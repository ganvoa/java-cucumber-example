package dev.udd.user.application;

import org.springframework.stereotype.Service;

import dev.udd.user.application.response.UserResponse;
import dev.udd.user.domain.User;

@Service
final public class UserMapper {

    public UserResponse response(User user) {

        UserResponse response = new UserResponse(user.username().value(), user.name().value(), user.email().value(),
                user.id().value());
        return response;
    }

}
