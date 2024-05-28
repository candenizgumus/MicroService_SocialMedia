package com.candenizgumus.dto;

import com.candenizgumus.domain.Status;

import java.time.LocalDateTime;

public record ElasticPostSaveRequest(String id,
                                     Long authId,
                                     String username,
                                     String context,


                                     LocalDateTime localDateTime,

                                     LocalDateTime updateAt,

                                     Status status,

                                     Integer likeCount,

                                     Integer reTweetCount)
{
}
