package dev.udd.user.application;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.udd.user.application.query.UserFindQuery;
import dev.udd.user.application.response.UserResponse;
import dev.udd.user.domain.User;
import dev.udd.user.domain.UserId;
import dev.udd.user.domain.UserMother;
import dev.udd.user.domain.UserNotFound;
import dev.udd.user.domain.UserRepository;
import dev.udd.user.domain.UserValueInvalid;

@ExtendWith(MockitoExtension.class)
final public class UserFinderTest {

    @Mock
    UserRepository userRepository;

    @Spy
    UserMapper userMapper;

    @InjectMocks
    UserFinder userFinder;

    @Test
    public void whenUserNotFoundShouldThrowUserNotFound() {

        assertThrows(UserNotFound.class, () -> {

            UserId userId = UserId.fromString("b8bd9278-b164-49a4-ad50-77df7ace8cec");
            UserFindQuery query = new UserFindQuery(userId.value());

            when(userRepository.getById(userId)).thenReturn(null);
            userFinder.find(query);
        });
    }

    @Test
    public void whenInvalidArgumentShouldThrowUserValueInvalid() {

        assertThrows(UserValueInvalid.class, () -> {

            UserFindQuery query = new UserFindQuery("b8bd9278-b16449a4ad5077df7ace8cec");
            userFinder.find(query);
        });

    }

    @Test
    public void whenUserExistsShouldReturnUserResponse() throws UserValueInvalid, UserNotFound {

        User user = UserMother.withId("b8bd9278-b164-49a4-ad50-77df7ace8cec");
        UserFindQuery query = new UserFindQuery(user.id().value());

        when(userRepository.getById(user.id())).thenReturn(user);
        UserResponse response = userFinder.find(query);

        assert response.getEmail().equals(user.email().value());
        assert response.getUuid().equals(user.id().value());
        assert response.getUuid().equals(user.id().value());
        assert response.getUsername().equals(user.username().value());
        assert response.getName().equals(user.name().value());
    }

}
