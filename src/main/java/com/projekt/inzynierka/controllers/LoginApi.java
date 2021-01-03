package com.projekt.inzynierka.controllers;

import com.projekt.inzynierka.model.User;
import com.projekt.inzynierka.responses.AuthenticationResponse;
import com.projekt.inzynierka.services.UserService;
import com.projekt.inzynierka.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginApi {


    private final UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginApi(final UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<?> login(@RequestBody final User userDTO) throws Exception {

        System.out.println(userDTO.getLogin() + " " + userDTO.getPassword());
        final User user = userService.getEntityByLoginAndisActive(userDTO.getLogin(), true);

        final Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (final BadCredentialsException e) {
            throw new Exception("Bad login creds", e);
        }

        final String token = jwtUtil.generateToken(authentication);

        final String role;

        role = user.getRole().getName();

        return ResponseEntity.ok(new AuthenticationResponse(token, role));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/name")
    public String hello() {

       return "hello";
    }
}
