package dev.udd.user.domain;

import org.apache.commons.validator.routines.EmailValidator;

public final class UserEmail {

    private String value;

    private UserEmail(String value) {

        this.value = value;
    }

    public static UserEmail fromString(String email) throws UserValueInvalid {

        if (email == null) {
            throw new UserValueInvalid("email is invalid");
        }

        var validator = EmailValidator.getInstance();

        if (!validator.isValid(email)) {
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
