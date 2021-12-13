package dev.udd.user.domain;

public class UserNotFound extends Throwable {

    public UserNotFound(UserId userId) {

        super("user not found: " + userId.value());
    }

}
