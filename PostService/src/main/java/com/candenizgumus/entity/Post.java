package com.candenizgumus.entity;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document
public class Post
{
    @MongoId
    String id;
    Long authId;
    String username;
    String context;


    @CreatedDate
    LocalDateTime localDateTime;
    @LastModifiedDate
    LocalDateTime updateAt;
    @Builder.Default
    Status status = Status.ACTIVE;
    @Builder.Default
    Integer likeCount = 0;
    @Builder.Default
    Integer reTweetCount = 0;


}
