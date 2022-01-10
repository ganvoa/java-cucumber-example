package dev.udd.user.domain;

import java.util.regex.Pattern;

public final class UserEmail {

    private String value;

    private UserEmail(String value) {

        this.value = value;
    }

    public static UserEmail fromString(String email) throws UserValueInvalid {

        if (email == null) {
            throw new UserValueInvalid("email is invalid");
        }

        boolean isValid = Pattern
                .compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
                .matcher(email).matches();

        if (!isValid) {
            throw new UserValueInvalid("email is invalid");
        }

        return new UserEmail(email);

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

        UserEmail that = (UserEmail) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {

        return value.hashCode();
    }

}
