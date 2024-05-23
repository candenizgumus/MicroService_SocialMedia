package com.candenizgumus.config.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SendEmailModel
{
    String from;
    String to;
    String subject;
    String message;
}
