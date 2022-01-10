package dev.udd.user.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import dev.udd.shared.application.ErrorResponse;
import dev.udd.user.application.UserUpdater;
import dev.udd.user.application.command.UserUpdateCommand;
import dev.udd.user.domain.UserAlreadyExists;
import dev.udd.user.domain.UserNotFound;
import dev.udd.user.domain.UserValueInvalid;

@RestController
public final class UserPutController {

    @Autowired
    public UserUpdater userUpdater;

    @PutMapping("/users/{userId}")
    public ResponseEntity<Object> putUser(@PathVariable String userId,
            @RequestBody UserUpdateCommand command) {

        command.setUuid(userId);

        try {
            this.userUpdater.update(command);
        } catch (UserAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponse(e.getMessage(), 403));
        } catch (UserValueInvalid e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), 400));
        } catch (UserNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(e.getMessage(), 404));
        }

        return ResponseEntity.ok().build();
    }

}
