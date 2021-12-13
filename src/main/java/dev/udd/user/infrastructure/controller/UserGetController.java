package dev.udd.user.infrastructure.controller;

import dev.udd.shared.application.ErrorResponse;
import dev.udd.user.application.UserFinder;
import dev.udd.user.application.query.UserFindQuery;
import dev.udd.user.application.response.UserResponse;
import dev.udd.user.domain.UserNotFound;
import dev.udd.user.domain.UserValueInvalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
final public class UserGetController {

    @Autowired
    public UserFinder service;

    @GetMapping("/users/{userId}")
    public ResponseEntity getUser(@PathVariable String userId) {

        UserFindQuery query = new UserFindQuery(userId);
        UserResponse userResponse = null;

        try {
            userResponse = this.service.find(query);
        } catch (UserNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage(), 404));
        } catch (UserValueInvalid e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage(), 400));
        }

        return ResponseEntity.ok().body(userResponse);
    }

}
