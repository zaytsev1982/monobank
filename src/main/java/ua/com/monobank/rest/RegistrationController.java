package ua.com.monobank.rest;

import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.monobank.transfer.UserForm;
import ua.com.monobank.users.UserServicePrincipal.service.UserService;
import ua.com.monobank.users.entity.User;

@RestController
@Builder
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(
        UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> registration(@RequestBody UserForm userForm) {
        User user = User.builder()
            .login(userForm.getLogin())
            .password(userForm.getPassword()).build();
        User userRegister = userService.userRegister(user);
        if (userRegister == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRegister, HttpStatus.OK);

    }
}
