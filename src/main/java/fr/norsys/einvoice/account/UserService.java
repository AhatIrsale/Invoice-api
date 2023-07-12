package fr.norsys.einvoice.account;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.List.of;

@Service
public class UserService {

    private final Keycloak keycloak;
    private final String realm;

    public UserService(Keycloak keycloak, @Value("${keycloak.admin.realm}") String realm) {
        this.realm = realm;
        this.keycloak = keycloak;
    }

    public List<UserDTO> findAll() {
        return keycloak.realm(realm).users().list().stream().map(usr -> new UserDTO(usr.getFirstName(), usr.getLastName())).collect(Collectors.toList());
    }

    public void createUser(UserRequest userRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEmail(userRequest.email());
        userRepresentation.setUsername(userRequest.username());
        userRepresentation.setLastName(userRepresentation.getLastName());
        userRepresentation.setFirstName(userRepresentation.getFirstName());
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userRequest.password());
        userRepresentation.setCredentials(of(passwordCred));
        keycloak.realm(realm).users().create(userRepresentation) ;
    }
}
