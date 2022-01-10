package dev.udd.user.infrastructure.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import dev.udd.shared.application.ErrorResponse;
import dev.udd.user.application.UserCreator;
import dev.udd.user.application.command.UserUpdateOrCreateCommand;
import dev.udd.user.domain.UserAlreadyExists;
import dev.udd.user.domain.UserValueInvalid;

@RestController
public final class UserPostController {

    @Autowired
    public UserCreator userCreator;

    @PostMapping("/users")
    public ResponseEntity<Object> postUser(@RequestBody UserUpdateOrCreateCommand command) {

        try {
            this.userCreator.create(command);
        } catch (UserAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponse(e.getMessage(), 403));
        } catch (UserValueInvalid e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), 400));
        }

        return ResponseEntity.created(URI.create("/users/" + command.getUuid())).build();
    }

}
