package dev.udd.user.application;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.udd.user.application.command.UserDeleteCommand;
import dev.udd.user.domain.User;
import dev.udd.user.domain.UserId;
import dev.udd.user.domain.UserMother;
import dev.udd.user.domain.UserNotFound;
import dev.udd.user.domain.UserRepository;
import dev.udd.user.domain.UserValueInvalid;

@ExtendWith(MockitoExtension.class)
final public class UserDeleterTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserDeleter userDeleter;

    @Test
    public void whenUserNotFoundShouldThrowUserNotFound() {

        assertThrows(UserNotFound.class, () -> {

            UserId userId = UserId.fromString("b8bd9278-b164-49a4-ad50-77df7ace8cec");
            UserDeleteCommand command = new UserDeleteCommand(userId.value());

            when(userRepository.getById(userId)).thenReturn(null);
            userDeleter.delete(command);
        });
    }

    @Test
    public void whenInvalidArgumentShouldThrowUserValueInvalid() {

        assertThrows(UserValueInvalid.class, () -> {

            UserDeleteCommand command = new UserDeleteCommand("b8bd9278-b16449a4ad5077df7ace8cec");
            userDeleter.delete(command);
        });

    }

    @Test
    public void whenUserExistsShouldDelete() throws UserValueInvalid, UserNotFound {

        User user = UserMother.withId("b8bd9278-b164-49a4-ad50-77df7ace8cec");
        UserDeleteCommand command = new UserDeleteCommand(user.id().value());

        when(userRepository.getById(user.id())).thenReturn(user);

        userDeleter.delete(command);

        Mockito.verify(userRepository, Mockito.times(1))
                .delete(argThat(userDeleted -> user.id().equals(userDeleted.id())));
    }

}
