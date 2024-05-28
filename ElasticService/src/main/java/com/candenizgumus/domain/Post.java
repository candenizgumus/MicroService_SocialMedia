package com.candenizgumus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.Document;


import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(indexName = "elasticpost")
public class Post
{
    @Id
    String id;
    Long authId;
    String username;
    String context;
    LocalDateTime localDateTime;
    LocalDateTime updateAt;
    Status status;
    Integer likeCount;
    Integer reTweetCount;

}
