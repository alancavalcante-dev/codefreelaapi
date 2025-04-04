package io.github.alancavalcante_dev.codefreelaapi.config;


import io.github.alancavalcante_dev.codefreelaapi.security.CustomAuthenticationProvider;
import io.github.alancavalcante_dev.codefreelaapi.security.CustomizeUserDetailsService;
import io.github.alancavalcante_dev.codefreelaapi.security.LoginSuccessHandlerCustom;
import io.github.alancavalcante_dev.codefreelaapi.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(CustomAuthenticationProvider authenticationProvider, HttpSecurity httpSecurity, LoginSuccessHandlerCustom loginSuccessHandlerCustom) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults()) // autenticação via aplicações
                .formLogin(formConfig -> { // autenticação via browser
                    formConfig.loginPage("/login").permitAll();
                })
                .authorizeHttpRequests(authorize -> { // configurações de rotas e permissões
                    authorize.requestMatchers("/login").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "api/users").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .oauth2Login(oauth -> {
                    oauth.loginPage("/login");
                    oauth.successHandler(loginSuccessHandlerCustom);
                })
                .oauth2ResourceServer(oauth2RS -> oauth2RS.jwt(Customizer.withDefaults())) // habilitar jwt para validar usuario
                .build();
    }



    // definindo prefixo da role, tirando "ROLE_"
    // Configura, no Http Basic, o prefixo ROLE

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }


    // SCOPE_GERENTE = padrão
    // Configura, no token Jwt, o prefixo SCOPE
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("");

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }
}
