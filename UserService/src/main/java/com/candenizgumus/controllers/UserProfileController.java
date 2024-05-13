package com.candenizgumus.controllers;

import static com.candenizgumus.constants.EndPoints.*;

import com.candenizgumus.dto.request.UserProfileSaveRequestDto;
import com.candenizgumus.entities.UserProfile;
import com.candenizgumus.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
