package fr.norsys.einvoice.account;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static java.util.List.of;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private Keycloak keycloak;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> findUsers() {
        return this.userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserRequest userRequest) {
        Objects.requireNonNull(userRequest.email(), "Email should not be null");
        Objects.requireNonNull(userRequest.password(), "Password should not be null");
        Objects.requireNonNull(userRequest.username(), "Username should not be null");
        this.userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}