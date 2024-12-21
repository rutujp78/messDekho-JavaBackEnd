package com.rutujp78.messDekho_backend.Controller;

import com.rutujp78.messDekho_backend.Model.Pin;
import com.rutujp78.messDekho_backend.Service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/pins")
public class PinController {

    @Autowired
    PinService pinService;

    @GetMapping
    public ResponseEntity<Object> getAllPins() throws Exception {
            return pinService.getAllPins();
    }

    @PostMapping
    public ResponseEntity<Object> createPin(@RequestBody Pin pin) throws Exception {
        return pinService.createPin(pin);
    }

    @PutMapping
    public ResponseEntity<Object> updatePin(@RequestBody Pin pin) throws Exception{
        return pinService.updatePin(pin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePin(@PathVariable String id) throws Exception{
        return pinService.deletePin(id);
    }
}
