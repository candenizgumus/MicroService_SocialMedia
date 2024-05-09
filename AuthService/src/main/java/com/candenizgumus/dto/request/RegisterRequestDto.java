package com.candenizgumus.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterRequestDto
{
    String username;
    @Size(min = 5 , message = "At least 5 char password")
    String password;
    @Email
    String email;
}
