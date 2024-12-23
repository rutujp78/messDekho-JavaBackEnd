package com.rutujp78.messDekho_backend.Controller;

import com.rutujp78.messDekho_backend.Model.User;
import com.rutujp78.messDekho_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) throws Exception{
        return userService.register(user);
    }

    @GetMapping("/getUser")
    public ResponseEntity<Object> getUser() throws Exception {
        return userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) throws Exception {
        return userService.login(user);

    }
}
