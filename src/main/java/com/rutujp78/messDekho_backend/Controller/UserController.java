package com.rutujp78.messDekho_backend.Controller;

import com.rutujp78.messDekho_backend.Model.User;
import com.rutujp78.messDekho_backend.Response.LoginResponse;
import com.rutujp78.messDekho_backend.Response.RegisterResponse;
import com.rutujp78.messDekho_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) throws Exception{
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) throws Exception {
//        return userService.login(user);
        return userService.login(user);

    }
}
