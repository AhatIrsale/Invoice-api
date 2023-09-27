package fr.norsys.einvoice.account;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.keycloak.representations.idm.RoleRepresentation;


import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.*;
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

    public  List<UserDTO> findAll() {
        return keycloak.realm(realm).users()
                .list()
                .stream()
                .map(usr -> new UserDTO(usr.getFirstName(), usr.getLastName(), usr.getRealmRoles()))
                .collect(Collectors.toList());

    }

    public List<UserDTO> getAllUsersWithRoles() {
        RealmResource realmResource = keycloak.realm(realm); // Replace with your realm name
        UsersResource usersResource = realmResource.users();

        // Retrieve all users
        List<UserRepresentation> allUsers = usersResource.list();

        // Retrieve roles for each user and create UserWithRolesDTO objects
        List<UserDTO> usersWithRoles = allUsers.stream()
                .map(user -> {
                    List<RoleRepresentation> userRoles = realmResource.users().get(user.getId()).roles().realmLevel().listAll();
                    List<String> userRoleNames = userRoles.stream()
                            .map(role -> role.getName())
                            .collect(Collectors.toList());
                    return new UserDTO(user.getFirstName(), user.getLastName(), userRoleNames);
                })
                .collect(Collectors.toList());

        return usersWithRoles;
    }


    /*public void createUser(UserRequest userRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEmail(userRequest.email());
        userRepresentation.setUsername(userRequest.username());
<<<<<<< HEAD
        userRepresentation.setLastName(userRequest.firstName());
        userRepresentation.setFirstName(userRequest.lastName());
        userRepresentation.setEnabled(true);
=======
        userRepresentation.setLastName(userRepresentation.getLastName());
        userRepresentation.setFirstName(userRepresentation.getFirstName());

        // Set the appropriate realm role
        List<String> realmRoles = List.of("ROLE_" + userRequest.role());
        System.out.println("role : " + userRequest.role() );
        //aded

        userRepresentation.setRealmRoles(realmRoles);

>>>>>>> origin/pfa-2023
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userRequest.password());
        userRepresentation.setCredentials(of(passwordCred));

        keycloak.realm(realm).users().create(userRepresentation) ;
    }*/


    public void createUser(UserRequest userRequest) {
        try {
            // Get the realm resource
            RealmResource realmResource = keycloak.realm(realm);

            // Create a new user representation
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setEmail(userRequest.email());
            userRepresentation.setUsername(userRequest.username());
            userRepresentation.setLastName(userRequest.lastName());
            userRepresentation.setFirstName(userRequest.firstName());

            // Set the user's enabled status to true
            userRepresentation.setEnabled(true);

            // Create the user in Keycloak
            Response response = realmResource.users().create(userRepresentation);
            System.out.println(userRequest.role());
            // Check the response status
            if (response.getStatus() == 201) {
                // User created successfully

                // Get the created user's ID from the response location header
                String userId = getUserIdFromLocationHeader(response.getLocation());

                // Log the user ID for debugging
                System.out.println("User ID: " + userId);

                // Get the role ID for the desired realm role (assuming "ROLE_" + userRequest.role())
                String roleId = userRequest.role();


                // Log the role ID for debugging
                System.out.println("Role ID: " + roleId);

                // Get the RoleRepresentation for the role
                RoleRepresentation roleRepresentation = realmResource.roles()
                        .get(roleId)
                        .toRepresentation();

                // Log the role representation for debugging
                System.out.println("Role Representation: " + roleRepresentation.getName());

                // Assign the realm role to the user
                realmResource.users().get(userId).roles().realmLevel().add(Arrays.asList(roleRepresentation));

                // Set the user's password
                CredentialRepresentation passwordCred = new CredentialRepresentation();
                passwordCred.setTemporary(false);
                passwordCred.setType(CredentialRepresentation.PASSWORD);
                passwordCred.setValue(userRequest.password());
                realmResource.users().get(userId).resetPassword(passwordCred);
            } else {
                // Handle the error condition (e.g., user creation failed)
                // You can log an error or throw an exception as needed
                System.err.println("User creation failed with status code: " + response.getStatus());
            }
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }

    private String getUserIdFromLocationHeader(URI locationHeader) {
        String path = locationHeader.getPath();
        return path.substring(path.lastIndexOf('/') + 1);
    }
    public Date calculateTrialExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        return calendar.getTime();
    }

}
