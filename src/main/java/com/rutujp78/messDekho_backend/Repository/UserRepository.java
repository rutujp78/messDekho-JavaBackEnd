package com.rutujp78.messDekho_backend.Repository;

import com.rutujp78.messDekho_backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String username);
    public User findByEmail(String email);
}
