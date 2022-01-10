package dev.udd.user.domain;

public class UserName {

    private String value;

    private UserName(String value) {

        this.value = value;
    }

    public static UserName fromString(String name) throws UserValueInvalid {

        if (name == null) {
            throw new UserValueInvalid("name is invalid");
        }

        return new UserName(name);
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

        UserName that = (UserName) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {

        return value.hashCode();
    }

}
