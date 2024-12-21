package com.rutujp78.messDekho_backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pins")
public class Pin {
    @Id
    private String id;

    private String username;

    private String title;
    private String desc;


    private Number rating;
    private Number lat;
    private Number lng;

//    OffsetDateTime dateTime = OffsetDateTime.now(ZoneOffset.UTC);
    private Date createdAt;
    private Date updatedAt;
}
