package dev.udd.user.domain;

public class User {

    private UserName name;
    private UserEmail email;
    private UserId userId;
    private UserPassword password;
    private UserUsername username;

    public User(UserId userId, UserName name, UserEmail email, UserUsername username,
            UserPassword password) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserName name() {

        return name;
    }

    public UserEmail email() {

        return email;
    }

    public UserId id() {

        return userId;
    }

    public UserPassword password() {

        return password;
    }

    public UserUsername username() {

        return username;
    }

}
