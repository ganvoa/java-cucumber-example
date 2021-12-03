package dev.udd.user.domain;

public class UserUsername {

    private String value;

    public UserUsername(String value) {

        this.value = value;
    }

    public static UserUsername fromString(String username) throws UserValueInvalid {

        if (username == null) {
            throw new UserValueInvalid("username is invalid");
        }

        return new UserUsername(username);
    }

    public String value() {

        return value;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserUsername that = (UserUsername) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {

        return value.hashCode();
    }

}

