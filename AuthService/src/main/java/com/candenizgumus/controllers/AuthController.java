package com.candenizgumus.controllers;

import static com.candenizgumus.constants.EndPoints.*;

import com.candenizgumus.dto.request.ActivateCodeRequestDto;
import com.candenizgumus.dto.request.LoginRequestDto;
import com.candenizgumus.dto.request.RegisterRequestDto;
import com.candenizgumus.dto.response.LoginResponseDto;
import com.candenizgumus.dto.response.RegisterResponseDto;
import com.candenizgumus.entities.Auth;
import com.candenizgumus.entities.BaseEntity;
import com.candenizgumus.enums.Role;
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

    @PostMapping("/gettoken")
    public ResponseEntity<String> getToken( Long id)
    {

        return ResponseEntity.ok(authService.getToken(id));
    }

    @PostMapping("/getroletoken")
    public ResponseEntity<String> getRoleToken( Long id , Role role)
    {

        return ResponseEntity.ok(authService.getRoleToken(id,role));
    }

    @GetMapping("/getidfromtoken")
    public ResponseEntity<Long> getIdFromToken( String token)
    {

        return ResponseEntity.ok(authService.getIdFromToken(token));
    }

    @GetMapping("/getRoleFromToken ")
    public ResponseEntity<String> getRoleFromToken( String token)
    {

        return ResponseEntity.ok(authService.getRoleFromToken (token));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Auth>> findAll(String token){
        return ResponseEntity.ok(authService.findAll(token));
    }

    @PutMapping(ACTIVATION)
    public ResponseEntity<String> activateUser(@RequestBody ActivateCodeRequestDto dto){
        return ResponseEntity.ok(authService.activateAuth(dto.getId(), dto.getActivationCode()));
    }



    @DeleteMapping(DELETE+"/{id}")
    public ResponseEntity<String> delete( @PathVariable("id") Long authId){
        return ResponseEntity.ok(authService.delete(authId));
    }
}
