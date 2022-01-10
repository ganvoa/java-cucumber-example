package dev.udd.user.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import dev.udd.shared.application.ErrorResponse;
import dev.udd.user.application.UserDeleter;
import dev.udd.user.application.command.UserDeleteCommand;
import dev.udd.user.domain.UserNotFound;
import dev.udd.user.domain.UserValueInvalid;

@RestController
public final class UserDeleteController {

    @Autowired
    public UserDeleter service;

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userId) {

        UserDeleteCommand command = new UserDeleteCommand(userId);

        try {
            this.service.delete(command);
        } catch (UserNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(e.getMessage(), 404));
        } catch (UserValueInvalid e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), 400));
        }

        return ResponseEntity.status(204).build();
    }

}
