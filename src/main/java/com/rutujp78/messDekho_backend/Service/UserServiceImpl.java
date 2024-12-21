package com.rutujp78.messDekho_backend.Service;

import com.rutujp78.messDekho_backend.Model.User;
import com.rutujp78.messDekho_backend.Repository.UserRepository;
import com.rutujp78.messDekho_backend.Response.LoginResponse;
import com.rutujp78.messDekho_backend.Response.RegisterResponse;
import com.rutujp78.messDekho_backend.Utility.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtUtils jwtUtils;

    private String getUserNotFoundMsg(String id) {
        return "User with given id: " + id + " does not exists.";
    }

    public Map<String, Object> getRespAsMessage(String msg) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", msg);
        return resp;
    }

    @Override
    public ResponseEntity<Object> getUserByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if(user == null) return new ResponseEntity<>(getRespAsMessage("User with username: " + username + " not found."), HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getUser(String id) throws Exception {
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isEmpty()) return new ResponseEntity<>(getRespAsMessage(getUserNotFoundMsg(id)), HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> login(@RequestBody User user) throws Exception {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()) {
            String jwtToken = jwtUtils.generateToken(user.getUsername());
            LoginResponse res = new LoginResponse();
            res.setUsername(user.getUsername());
            res.setToken(jwtToken);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(getRespAsMessage("Invalid credentials."), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Object> register(User user) throws Exception {
        if(
            user.getUsername() == null ||
            user.getPassword() == null ||
            user.getEmail() == null
        ) {
            return new ResponseEntity<>(getRespAsMessage("Incomplete User details."), HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        String hashedPass = new BCryptPasswordEncoder().encode(user.getPassword());
        newUser.setPassword(hashedPass);
        newUser.setEmail(user.getEmail());

        User registeredUser = userRepository.save(newUser);

        RegisterResponse resp = new RegisterResponse();
        resp.set_id(registeredUser.getId());

        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }
}
