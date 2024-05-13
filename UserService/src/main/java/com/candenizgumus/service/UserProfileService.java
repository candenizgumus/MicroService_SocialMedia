package com.candenizgumus.service;

import com.candenizgumus.dto.request.UserProfileSaveRequestDto;
import com.candenizgumus.entities.UserProfile;
import com.candenizgumus.mapper.UserProfileMapper;
import com.candenizgumus.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService
{
    private final UserProfileRepository userProfileRepository;

    public UserProfile save(UserProfileSaveRequestDto dto)
    {
        UserProfile userProfile = UserProfileMapper.INSTANCE.dtoToUserProfile(dto);
        return userProfileRepository.save(userProfile   );
    }
}
