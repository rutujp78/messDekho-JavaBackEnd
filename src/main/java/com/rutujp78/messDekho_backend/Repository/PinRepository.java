package com.rutujp78.messDekho_backend.Repository;

import com.rutujp78.messDekho_backend.Model.Pin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PinRepository extends MongoRepository<Pin, String> {
}
