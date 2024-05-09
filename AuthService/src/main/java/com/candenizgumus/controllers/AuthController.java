package com.candenizgumus.controllers;

import static com.candenizgumus.constants.EndPoints.*;

import com.candenizgumus.dto.request.LoginRequestDto;
import com.candenizgumus.dto.request.RegisterRequestDto;
import com.candenizgumus.dto.response.LoginResponseDto;
import com.candenizgumus.dto.response.RegisterResponseDto;
import com.candenizgumus.entities.Auth;
import com.candenizgumus.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController
{
    private final AuthService authService;

    /**
     * Register işlemleri:
     */
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto)
    {
        return ResponseEntity.ok(authService.save(dto));
    }

    /**
     * Login İşlemleri
     */
    @PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody LoginRequestDto dto)
    {

        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Auth>> findAll(String token){
        return ResponseEntity.ok(authService.findAll(token));
    }
}
