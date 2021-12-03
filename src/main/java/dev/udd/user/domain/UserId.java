package dev.udd.user.domain;

import java.util.UUID;

public class UserId {

    private String value;

    private UserId(UUID value) {

        this.value = value.toString();
    }

    public static UserId fromString(String userId) throws UserValueInvalid {

        UUID id = null;

        try {
            id = UUID.fromString(userId);
        } catch (IllegalArgumentException e) {
            throw new UserValueInvalid("uuid is invalid");
        }
        return new UserId(id);
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

        UserId that = (UserId) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {

        return value.hashCode();
    }

}
