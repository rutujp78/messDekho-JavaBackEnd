package com.rutujp78.messDekho_backend.Service;

import com.rutujp78.messDekho_backend.Model.User;
import com.rutujp78.messDekho_backend.Response.LoginResponse;
import com.rutujp78.messDekho_backend.Response.RegisterResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface UserService {
    public ResponseEntity<Object> getUserByUsername(String username) throws Exception;
    public ResponseEntity<Object> getUser(String id) throws Exception;
    public ResponseEntity<Object> login(@RequestBody User user) throws Exception;
    public ResponseEntity<Object> register(@RequestBody User user) throws Exception;

}
