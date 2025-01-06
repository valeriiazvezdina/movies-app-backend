package com.zvezdval.movies_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatusCode;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private String id;
    private String body;

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Review(String body) {
        this.body = body;
    }
}
