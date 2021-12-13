package dev.udd.user.domain;

public class UserAlreadyExists extends Throwable {

    public UserAlreadyExists(UserId userId) {

        super("user id already exists: " + userId.value());
    }

    public UserAlreadyExists(UserEmail email) {

        super("user email already exists: " + email.value());
    }

    public UserAlreadyExists(UserUsername username) {

        super("username already exists: " + username.value());
    }

}
