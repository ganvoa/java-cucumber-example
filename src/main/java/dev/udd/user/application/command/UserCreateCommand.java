package dev.udd.user.application.command;

final public class UserCreateCommand {

    private String name;
    private String username;
    private String uuid;
    private String password;
    private String email;

    public UserCreateCommand(String name, String username, String uuid, String password, String email) {

        this.name = name;
        this.username = username;
        this.uuid = uuid;
        this.password = password;
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public String getUsername() {

        return username;
    }

    public String getUuid() {

        return uuid;
    }

    public String getPassword() {

        return password;
    }

    public String getEmail() {

        return email;
    }

}
