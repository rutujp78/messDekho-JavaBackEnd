package com.rutujp78.messDekho_backend.Service;

import com.rutujp78.messDekho_backend.Model.Pin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PinService {
    public ResponseEntity<Object> getAllPins() throws Exception;

    public ResponseEntity<Object> createPin(Pin pin) throws Exception;

    public ResponseEntity<Object> updatePin(Pin pin) throws Exception;

    public ResponseEntity<Object> deletePin(String id) throws Exception;
}
