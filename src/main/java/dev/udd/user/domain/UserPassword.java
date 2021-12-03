package dev.udd.user.domain;

public class UserPassword {

    private String value;

    private UserPassword(String value) {

        this.value = value;
    }

    public static UserPassword fromString(String password) throws UserValueInvalid {

        if (password == null) {
            throw new UserValueInvalid("password is invalid");
        }

        return new UserPassword(password);
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

        UserPassword that = (UserPassword) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {

        return value.hashCode();
    }

}
