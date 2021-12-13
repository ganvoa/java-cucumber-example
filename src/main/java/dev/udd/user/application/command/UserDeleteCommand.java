package dev.udd.user.application.command;

public class UserDeleteCommand {

    private String uuid;

    public UserDeleteCommand(String uuid) {

        this.uuid = uuid;
    }

    public String getUuid() {

        return uuid;
    }

}
