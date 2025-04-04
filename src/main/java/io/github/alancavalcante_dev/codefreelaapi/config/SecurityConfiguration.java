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
                .authenticationProvider(authenticationProvider) // Aqui garantimos que o provider será usado!
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


//    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new CustomizeUserDetailsService(userService);
    }

    // definindo prefixo da role, tirando "ROLE_"
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
}
