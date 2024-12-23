package com.rutujp78.messDekho_backend.Service;

import com.rutujp78.messDekho_backend.Model.Pin;
import com.rutujp78.messDekho_backend.Repository.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PinServiceImpl implements PinService{
    private final String unauthorizedMsg = "You are not authorized to create, update and delete this pin.";
    @Autowired
    PinRepository pinRepository;

    private String getAuthenticatedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String getNotFoundMsg(String id) {
        return "Pin with id: " + id + " not found.";
    }

    public Map<String, Object> getRespAsMessage(String msg) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", msg);
        return resp;
    }

    @Override
    public ResponseEntity<Object> getAllPins() throws Exception {
        return new ResponseEntity<>(pinRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> createPin(Pin pin) throws Exception {
        String authenticatedUsername = getAuthenticatedUsername();

        if(!authenticatedUsername.equals(pin.getUsername())) {
            return new ResponseEntity<>(unauthorizedMsg, HttpStatus.FORBIDDEN);
        }

        Pin newPin = new Pin();
        newPin.setUsername(pin.getUsername());
        newPin.setTitle(pin.getTitle());
        newPin.setDesc(pin.getDesc());
        newPin.setRating(pin.getRating());
        newPin.setLat(pin.getLat());
        newPin.setLng(pin.getLng());
        Date date = new Date();
        newPin.setCreatedAt(date);
        newPin.setUpdatedAt(date);

        return new ResponseEntity<>(pinRepository.save(newPin), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updatePin(Pin pin) throws Exception {
        Optional<Pin> optPin = pinRepository.findById(pin.getId());
        if(optPin.isPresent()) {
            Pin oldPin = optPin.get();

            String authenticatedUsername = getAuthenticatedUsername();

            if(!authenticatedUsername.equals(oldPin.getUsername()) || !authenticatedUsername.equals(pin.getUsername())) {
                return new ResponseEntity<>(getRespAsMessage(unauthorizedMsg), HttpStatus.FORBIDDEN);
            }

            if(pin.getTitle() != null && !pin.getTitle().isEmpty()) {
                oldPin.setTitle(pin.getTitle());
            }
            if(pin.getDesc() != null && !pin.getDesc().isEmpty()) {
                oldPin.setDesc(pin.getDesc());
            }
            if(pin.getRating() != null ) {
                if((int)pin.getRating() > 0 && (int)pin.getRating() <= 5)
                    oldPin.setRating((int)pin.getRating());
                else return new ResponseEntity<>(getRespAsMessage("Rating must be between 0 and 5"), HttpStatus.BAD_REQUEST);
            }

            Date date = new Date();
            oldPin.setUpdatedAt(date);

            return new ResponseEntity<>(pinRepository.save(oldPin), HttpStatus.OK);
        }
        else {
            String msg = getNotFoundMsg(pin.getId());
            return new ResponseEntity<>(getRespAsMessage(msg), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> deletePin(String id) throws Exception {
        Optional<Pin> pin = pinRepository.findById(id);
        if(pin.isEmpty()) {
            String msg = getNotFoundMsg(id);
            return new ResponseEntity<>(getRespAsMessage(msg), HttpStatus.NOT_FOUND);
        }

        String authenticatedUsername = getAuthenticatedUsername();

        if(!authenticatedUsername.equals(pin.get().getUsername())) {
            return new ResponseEntity<>(getRespAsMessage(unauthorizedMsg), HttpStatus.FORBIDDEN);
        }

        pinRepository.deleteById(id);
        return new ResponseEntity<>(pin.get(), HttpStatus.ACCEPTED);
    }
}
