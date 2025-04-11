package io.github.alancavalcante_dev.codefreelaapi.security;

import io.github.alancavalcante_dev.codefreelaapi.model.User;
import io.github.alancavalcante_dev.codefreelaapi.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;


import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandlerCustom extends SavedRequestAwareAuthenticationSuccessHandler {

    private final static String SENHA_PADRAO = "321";
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        User user = userService.getByEmail(email);
        System.out.println(user);

        if (user == null) {
            user = registerUser(email);
        }

        authentication = new CustomAuthentication(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        super.onAuthenticationSuccess(request, response, authentication);

    }

    private User registerUser(String email) {
        var usuario = new User();
        usuario.setEmail(email);
        usuario.setUsername(getLoginByEmail(email));
        usuario.setPassword(SENHA_PADRAO);
        usuario.setRoles(List.of("OPERADOR"));
        return userService.save(usuario);
    }

    public String getLoginByEmail(String email) {
        return email.substring(0, email.indexOf("@"));
    }

}
