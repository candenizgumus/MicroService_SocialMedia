package com.candenizgumus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Builder.Default
    LocalDateTime localDateTime = LocalDateTime.now();
    @Builder.Default
    Integer likeCount = 0;
    @Builder.Default
    Integer reTweetCount = 0;

}
