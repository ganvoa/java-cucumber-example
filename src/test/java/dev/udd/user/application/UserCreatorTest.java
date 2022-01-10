package dev.udd.user.application;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import dev.udd.user.application.command.UserCreateCommand;
import dev.udd.user.domain.User;
import dev.udd.user.domain.UserAlreadyExists;
import dev.udd.user.domain.UserMother;
import dev.udd.user.domain.UserRepository;
import dev.udd.user.domain.UserValueInvalid;

@ExtendWith(MockitoExtension.class)
public class UserCreatorTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserCreator userCreator;

    @Test
    public void whenUserIsValidExpectNoException() throws UserAlreadyExists, UserValueInvalid {

        User user = UserMother.withId("b8bd9278-b164-49a4-ad50-77df7ace8cec");

        UserCreateCommand command =
                new UserCreateCommand(user.name().value(), user.username().value(),
                        user.id().value(), user.password().value(), user.email().value());
        userCreator.create(command);

        Mockito.verify(userRepository, Mockito.times(1))
                .save(argThat(userSaved -> user.id().equals(userSaved.id())
                        & user.email().equals(userSaved.email())
                        & user.name().equals(userSaved.name())
                        & user.username().equals(userSaved.username())
                        & user.password().equals(userSaved.password())));

    }

    @Test
    public void whenUserAlreadyUidCreatedThrowUserAlreadyExists() {

        assertThrows(UserAlreadyExists.class, () -> {
            User user = UserMother.random();

            when(userRepository.getById(user.id())).thenReturn(user);

            UserCreateCommand command =
                    new UserCreateCommand(user.name().value(), user.username().value(),
                            user.id().value(), user.password().value(), user.email().value());
            userCreator.create(command);
        });

    }

    @ParameterizedTest
    @CsvSource({
            "juan perez,jperez,b8bd9278b16449a4ad5077df7ace8cec,changemepls,jperez@mail.com, uuid is invalid",
            "juan perez,,b8bd9278-b164-49a4-ad50-77df7ace8cec,changemepls,jperez@mail.com, username is invalid",
            ",jperez,b8bd9278-b164-49a4-ad50-77df7ace8cec,changemepls,jperez@mail.com, name is invalid",
            "juan perez,jperez,b8bd9278-b164-49a4-ad50-77df7ace8cec,,jperez@mail.com, password is invalid",
            "juan perez,jperez,b8bd9278-b164-49a4-ad50-77df7ace8cec,changemepls,jperez, email is invalid"})
    public void whenInvalidValueThrowUserValueInvalid(String name, String username, String uuid,
            String password, String email, String excpectedMessage) {

        UserValueInvalid userValueInvalid = assertThrows(UserValueInvalid.class, () -> {
            UserCreateCommand command =
                    new UserCreateCommand(name, username, uuid, password, email);
            userCreator.create(command);
        });

        Assertions.assertEquals(excpectedMessage, userValueInvalid.getMessage());

    }

    @Test
    public void whenUserAlreadyUsernameCreatedThrowUserAlreadyExists() {

        assertThrows(UserAlreadyExists.class, () -> {
            User user = UserMother.random();

            when(userRepository.getByUsername(user.username())).thenReturn(user);

            UserCreateCommand command =
                    new UserCreateCommand(user.name().value(), user.username().value(),
                            user.id().value(), user.password().value(), user.email().value());
            userCreator.create(command);
        });

    }

    @Test
    public void whenUserAlreadyEmailCreatedThrowUserAlreadyExists() {

        assertThrows(UserAlreadyExists.class, () -> {
            User user = UserMother.random();

            when(userRepository.getByEmail(user.email())).thenReturn(user);

            UserCreateCommand command =
                    new UserCreateCommand(user.name().value(), user.username().value(),
                            user.id().value(), user.password().value(), user.email().value());
            userCreator.create(command);
        });

    }

}
