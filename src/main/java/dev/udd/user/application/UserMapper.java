package dev.udd.user.application;

import dev.udd.user.application.response.UserResponse;
import dev.udd.user.domain.User;
import org.springframework.stereotype.Service;

@Service
final public class UserMapper {

    public UserResponse response(User user) {

        UserResponse response = new UserResponse(user.username().value(), user.name().value(), user.email().value(), user.id().value());
        return response;
    }

}
