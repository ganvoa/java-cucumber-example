package dev.udd.user.domain;

import com.github.javafaker.Faker;

public final class UserMother {

    public static User withId(String uuid) throws UserValueInvalid {

        UserId userId = UserId.fromString(uuid);
        UserName name = UserName.fromString(Faker.instance().name().fullName());
        UserEmail email = UserEmail.fromString(Faker.instance().internet().emailAddress());
        UserUsername username = UserUsername.fromString(Faker.instance().name().username());
        UserPassword password = UserPassword.fromString(Faker.instance().internet().password());

        return new User(userId, name, email, username, password);
    }

    public static User withUsername(String userName) throws UserValueInvalid {

        UserId userId = UserId.fromString(Faker.instance().internet().uuid());
        UserName name = UserName.fromString(Faker.instance().name().fullName());
        UserEmail email = UserEmail.fromString(Faker.instance().internet().emailAddress());
        UserUsername username = UserUsername.fromString(userName);
        UserPassword password = UserPassword.fromString(Faker.instance().internet().password());

        return new User(userId, name, email, username, password);
    }

    public static User withEmail(String userEmail) throws UserValueInvalid {

        UserId userId = UserId.fromString(Faker.instance().internet().uuid());
        UserName name = UserName.fromString(Faker.instance().name().fullName());
        UserEmail email = UserEmail.fromString(userEmail);
        UserUsername username = UserUsername.fromString(Faker.instance().name().username());
        UserPassword password = UserPassword.fromString(Faker.instance().internet().password());

        return new User(userId, name, email, username, password);
    }

    public static User random() throws UserValueInvalid {

        UserId userId = UserId.fromString(Faker.instance().internet().uuid());
        UserName name = UserName.fromString(Faker.instance().name().fullName());
        UserEmail email = UserEmail.fromString(Faker.instance().internet().emailAddress());
        UserUsername username = UserUsername.fromString(Faker.instance().name().username());
        UserPassword password = UserPassword.fromString(Faker.instance().internet().password());

        return new User(userId, name, email, username, password);
    }

}
