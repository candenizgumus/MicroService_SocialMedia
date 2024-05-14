package com.candenizgumus.entities;

import com.candenizgumus.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document
public class UserProfile
{
    @MongoId
    String id;
    Long authId;
    String username;
    String email;
    String phone;
    String photo;
    String address;
    String about;
    @Builder.Default
    Status status = Status.PENDING; //TODO SONRA BAKILACAK ANOTASYONUNA
}
