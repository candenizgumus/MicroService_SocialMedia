package com.candenizgumus.dto.request;

import com.candenizgumus.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserProfileUpdateRequest
{

    String token;
    Long authId;
    String email;
    String phone;
    String photo;
    String address;
    String about;
    Status status;
}
