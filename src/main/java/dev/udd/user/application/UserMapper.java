package dev.udd.user.application;

import org.springframework.stereotype.Service;
import dev.udd.user.application.response.UserResponse;
import dev.udd.user.domain.User;

@Service
public final class UserMapper {

    public UserResponse response(User user) {

        return new UserResponse(user.username().value(), user.name().value(), user.email().value(),
                user.id().value());
    }

}
