package com.candenizgumus.dto.request;

import com.candenizgumus.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoginRequestDto
{
    String username;
    String password;


}
