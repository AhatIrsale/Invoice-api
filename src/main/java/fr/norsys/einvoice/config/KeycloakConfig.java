package fr.norsys.einvoice.config;

import lombok.Getter;
import lombok.Setter;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties("keycloak.admin")
@Configuration
@Getter
@Setter
public class KeycloakConfig {

    private String serverUrl;
    private String clientId;
    private String realm;
    private String username;
    private String password;
    private String grantType;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
            .serverUrl(serverUrl)
            .realm(realm)
            .clientId(clientId)
            .grantType(grantType)
            .username(username)
            .password(password)
            .build();
    }
}
