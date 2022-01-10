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

import dev.udd.user.application.command.UserUpdateCommand;
import dev.udd.user.domain.User;
import dev.udd.user.domain.UserAlreadyExists;
import dev.udd.user.domain.UserMother;
import dev.udd.user.domain.UserNotFound;
import dev.udd.user.domain.UserRepository;
import dev.udd.user.domain.UserValueInvalid;

@ExtendWith(MockitoExtension.class)
final public class UserUpdaterTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserUpdater userUpdater;

    @Test
    public void whenUserIsValidExpectNoException() throws UserAlreadyExists, UserValueInvalid, UserNotFound {

        User user = UserMother.withId("b8bd9278-b164-49a4-ad50-77df7ace8cec");

        when(userRepository.getById(user.id())).thenReturn(user);
        UserUpdateCommand command = new UserUpdateCommand(user.name().value(), user.username().value(),
                user.id().value(), user.password()
                        .value(),
                user.email().value());
        userUpdater.update(command);

        Mockito.verify(userRepository, Mockito.times(1))
                .save(argThat(userSaved -> user.id().equals(userSaved.id()) & user.email().equals(userSaved.email())
                        & user.name()
                                .equals(userSaved.name())
                        & user.username().equals(userSaved.username()) & user.password()
                                .equals(userSaved.password())));

    }

    @Test
    public void whenUsernameAlreadyCreatedThrowUserAlreadyExists() {

        assertThrows(UserAlreadyExists.class, () -> {
            User user = UserMother.withUsername("pepito");
            User userAlreadyExists = UserMother.withUsername("pepito");

            when(userRepository.getById(user.id())).thenReturn(user);
            when(userRepository.getByUsername(user.username())).thenReturn(userAlreadyExists);

            UserUpdateCommand command = new UserUpdateCommand(user.name().value(), user.username().value(), user.id()
                    .value(), user.password().value(), user.email().value());
            userUpdater.update(command);
        });

    }

    @Test
    public void whenUserEmailAlreadyCreatedThrowUserAlreadyExists() {

        assertThrows(UserAlreadyExists.class, () -> {
            User user = UserMother.withEmail("pepito@test.cl");
            User userAlreadyExists = UserMother.withEmail("pepito@test.cl");

            when(userRepository.getById(user.id())).thenReturn(user);
            when(userRepository.getByEmail(user.email())).thenReturn(userAlreadyExists);

            UserUpdateCommand command = new UserUpdateCommand(user.name().value(), user.username().value(), user.id()
                    .value(), user.password().value(), user.email().value());
            userUpdater.update(command);
        });

    }

    @ParameterizedTest
    @CsvSource({
            "juan perez,jperez,b8bd9278b16449a4ad5077df7ace8cec,changemepls,jperez@mail.com, uuid is invalid",
            "juan perez,,b8bd9278-b164-49a4-ad50-77df7ace8cec,changemepls,jperez@mail.com, username is invalid",
            ",jperez,b8bd9278-b164-49a4-ad50-77df7ace8cec,changemepls,jperez@mail.com, name is invalid",
            "juan perez,jperez,b8bd9278-b164-49a4-ad50-77df7ace8cec,,jperez@mail.com, password is invalid",
            "juan perez,jperez,b8bd9278-b164-49a4-ad50-77df7ace8cec,changemepls,jperez, email is invalid"
    })
    public void whenInvalidValueThrowUserValueInvalid(String name, String username, String uuid, String password,
            String email,
            String expectedMessage) {

        UserValueInvalid userValueInvalid = assertThrows(UserValueInvalid.class, () -> {
            UserUpdateCommand command = new UserUpdateCommand(name, username, uuid, password, email);
            userUpdater.update(command);
        });

        Assertions.assertEquals(expectedMessage, userValueInvalid.getMessage());

    }

}
