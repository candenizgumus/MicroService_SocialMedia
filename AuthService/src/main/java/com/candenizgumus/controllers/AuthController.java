package com.candenizgumus.controllers;

import com.candenizgumus.dto.request.*;
import com.candenizgumus.dto.response.GetAllTweetsResponseDto;
import com.candenizgumus.dto.response.RegisterResponseDto;
import com.candenizgumus.entities.Auth;
import com.candenizgumus.enums.Role;
import com.candenizgumus.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.candenizgumus.constants.EndPoints.*;


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
    public ResponseEntity<List<Auth>> findAll(){
        return ResponseEntity.ok(authService.findAll());
    }

    @PutMapping(ACTIVATION)
    public ResponseEntity<String> activateUser(@RequestBody ActivateCodeRequestDto dto){
        return ResponseEntity.ok(authService.activateAuth(dto.getId(), dto.getActivationCode()));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> updateUserProfile(@RequestBody UserProfileUpdateRequest dto){
        return ResponseEntity.ok(authService.updateUserProfile(dto));
    }

    @DeleteMapping(DELETE+"/{id}")
    public ResponseEntity<String> delete( @PathVariable("id") Long authId){
        return ResponseEntity.ok(authService.delete(authId));
    }

    @PostMapping(POSTTWEET)
    public ResponseEntity<String> postTweet(@RequestBody PostTweetRequestDto dto){

        return ResponseEntity.ok(authService.postTweet(dto));

    }

    @GetMapping(GETMYTWEETS+"/{authId}")
    public ResponseEntity<List<String>> getMyTweets(@PathVariable Long authId){

        return ResponseEntity.ok(authService.getMyTweets(authId));

    }

    @GetMapping("getalltweets")
    public ResponseEntity<List<GetAllTweetsResponseDto>> getAllTweets(){
        return ResponseEntity.ok(authService.getAllTweets());
    }

    @PutMapping("/updatetweet")
    public ResponseEntity<String> updateTweet(@RequestBody UpdatePostTweetRequestDto dto){
        return ResponseEntity.ok(authService.updateTweet(dto));

    }


}
