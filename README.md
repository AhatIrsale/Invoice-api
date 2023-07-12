## Run the app

1. run Postgres && keycloak
```shell
cd docker && docker-compose up -d
```
2. connect to keycloak administration console `http://localhost:8080/admin` user/pass `admin/admin`

3. Import realm `docker/realm/realm-export.json`to keycloak
![import_realm.png](documentation%2Fimport_realm.png)
4. Create two users (admin, john)

## Dev workflow
![dev_workflow.png](documentation%2Fdev_workflow.png)
## Get all existing roles

```java
import java.security.Principal;

public class UserService {
    @Autowired
    Keycloak keycloak;
    String REALM = "e-invoice-web";

    public List<String> getRolesByUser(Principal principal) {
        return keycloak.realm(REALM).users().get(principal.getName()).roles().realmLevel().listAll();
    }
}
```
