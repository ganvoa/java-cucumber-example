package dev.udd.user.application.query;

public class UserFindQuery {

    private String uuid;

    public UserFindQuery(String uuid) {

        this.uuid = uuid;
    }

    public String getUuid() {

        return uuid;
    }

}
