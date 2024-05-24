package com.candenizgumus.controllers;

import static com.candenizgumus.constants.EndPoints.*;

import com.candenizgumus.dto.request.ActivateCodeRequestDto;
import com.candenizgumus.dto.request.UserProfileSaveRequestDto;
import com.candenizgumus.dto.request.UserProfileUpdateRequest;
import com.candenizgumus.entities.UserProfile;
import com.candenizgumus.enums.Status;
import com.candenizgumus.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(USERPROFILE)
@RequiredArgsConstructor
public class UserProfileController
{
    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<UserProfile> save(@RequestBody UserProfileSaveRequestDto dto){
        return ResponseEntity.ok(userProfileService.save(dto));

    }

    @PutMapping(ACTIVATION)
    public ResponseEntity<String> activateUser( @RequestBody Long authId){
        return ResponseEntity.ok(userProfileService.activateUser(authId));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> update(@RequestBody UserProfileUpdateRequest dto){
        return ResponseEntity.ok(userProfileService.update(dto));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<String> delete(@RequestBody Long authId){
        return ResponseEntity.ok(userProfileService.delete(authId));
    }

    @GetMapping("/findbyusername")
    public ResponseEntity<UserProfile> findByUsername(String username){
        return ResponseEntity.ok(userProfileService.findByUsername(username));
    }

    @GetMapping("/findAllByStatus")
    public ResponseEntity<List<UserProfile>> findAllByStatus(Status status){
        return ResponseEntity.ok(userProfileService.findAllByStatus(status));
    }


}
