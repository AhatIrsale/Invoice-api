package fr.norsys.einvoice.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    private final CustomAuthenticationConverter converter;

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));// Replace with your actual frontend origin
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Add the HTTP methods you want to allow
        configuration.setAllowedHeaders(List.of("*")); // Add the headers you want to allow
        configuration.setAllowCredentials(true); // Allow credentials (e.g., cookies) to be sent in CORS requests

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    /*  @Bean
  /*  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
                  .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS support

                  .authorizeHttpRequests(authorize -> authorize
                          .anyRequest().authenticated()
                  )
                  .oauth2ResourceServer(oauth2 -> oauth2
                          .jwt(jwt -> jwt
                                  .decoder(jwtDecoder())
                                  .jwtAuthenticationConverter(converter)
                          )
                  );
          return http.build();
      }*/


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        RequestMatcher adminRequestMatcher = new AntPathRequestMatcher("/api/users");
        RequestMatcher vipRequestMatcher = new AntPathRequestMatcher("/api/vip/**");
        RequestMatcher commercialRequestMatcher = new AntPathRequestMatcher("/api/commercial/**");
        RequestMatcher normalRequestMatcher = new AntPathRequestMatcher("/api/normal/**");

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeRequests(authorize -> authorize
                       .requestMatchers(adminRequestMatcher).hasRole("ADMIN")
                        .requestMatchers(vipRequestMatcher).hasRole("VIP")
                        .requestMatchers(commercialRequestMatcher).hasRole("COMMERCIAL")
                        .requestMatchers(normalRequestMatcher).hasRole("NORMAL")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .decoder(jwtDecoder())
                                .jwtAuthenticationConverter(converter)
                        )
                );

        return http.build();
    }
}
