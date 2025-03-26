package io.github.alancavalcante_dev.codefreelaapi.config;


import io.github.alancavalcante_dev.codefreelaapi.security.CustomizeUserDetailsService;
import io.github.alancavalcante_dev.codefreelaapi.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults()) // autenticação via aplicações
                .formLogin(formConfig -> { // autenticação via browser
                    formConfig.loginPage("/login").permitAll();
                })
                .authorizeHttpRequests(authorize -> { // configurações de rotas e permissões
                    authorize.requestMatchers("/login").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new CustomizeUserDetailsService(userService);
    }
}
