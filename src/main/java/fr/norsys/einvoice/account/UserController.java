package fr.norsys.einvoice.account;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

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
@GetMapping("/roles")
public List<UserDTO> getAllRoles(){
    return this.userService.getAllUsersWithRoles();
}

   @PreAuthorize("hasRole('ADMIN') or hasRole('VIP') or hasRole('NORMAL') or hasRole('COMMERCIAL')")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserRequest userRequest) {
        Objects.requireNonNull(userRequest.email(), "Email should not be null");
        Objects.requireNonNull(userRequest.password(), "Password should not be null");
        Objects.requireNonNull(userRequest.username(), "Username should not be null");
       // System.out.println(userRequest.role());
        this.userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


}
