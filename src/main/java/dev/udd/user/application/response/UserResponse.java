package dev.udd.user.application.response;

final public class UserResponse {

    private String username;
    private String name;
    private String email;
    private String uuid;

    public UserResponse(String username, String name, String email, String uuid) {

        this.username = username;
        this.name = name;
        this.email = email;
        this.uuid = uuid;
    }

    public String getUsername() {

        return username;
    }

    public String getName() {

        return name;
    }

    public String getEmail() {

        return email;
    }

    public String getUuid() {

        return uuid;
    }

}
