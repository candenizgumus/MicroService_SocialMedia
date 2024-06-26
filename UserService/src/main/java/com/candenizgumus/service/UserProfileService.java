package com.candenizgumus.service;

import com.candenizgumus.config.model.UserProfileModel;
import com.candenizgumus.dto.request.UserProfileSaveRequestDto;
import com.candenizgumus.dto.request.UserProfileUpdateRequest;
import com.candenizgumus.entities.UserProfile;
import com.candenizgumus.enums.Status;
import com.candenizgumus.exceptions.ErrorType;
import com.candenizgumus.exceptions.UserServiceException;
import com.candenizgumus.mapper.UserProfileMapper;
import com.candenizgumus.repository.UserProfileRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService
{
    private final UserProfileRepository userProfileRepository;
    private HashOperations<String, String, UserProfile> hashOperations;
    private final RedisTemplate<String, UserProfile> redisTemplate;
    private static final String KEY = "UserList";


    public UserProfile save(UserProfileSaveRequestDto dto)
    {
        UserProfile userProfile = UserProfileMapper.INSTANCE.dtoToUserProfile(dto);
        return userProfileRepository.save(userProfile);
    }

    @RabbitListener(queues = "register")
    @CacheEvict(value = "userprofiles", allEntries = true)
    public UserProfile saveWithRabbit(UserProfileSaveRequestDto dto) {
        UserProfile userProfile = UserProfileMapper.INSTANCE.dtoToUserProfile(dto);
        userProfileRepository.save(userProfile);
        return userProfile;
    }





    public String activateUser(Long authId)
    {
        UserProfile user = userProfileRepository.findByAuthId(authId).orElseThrow(() -> new UserServiceException(ErrorType.AUTH_NOT_FOUND));
        if (user.getStatus() != Status.PENDING)
        {
            throw new UserServiceException(ErrorType.ACCOUNT_STATUS_ERROR);
        }


        user.setStatus(Status.ACTIVE);
        userProfileRepository.save(user);
        return "Aktivasyon başarılı sisteme girebilirsiniz.";

    }

    @RabbitListener(queues = "activate")
    public void activateWithRabbit(Long authId)
    {
        UserProfile user = userProfileRepository.findByAuthId(authId).orElseThrow(() -> new UserServiceException(ErrorType.AUTH_NOT_FOUND));
        if (user.getStatus() != Status.PENDING)
        {
            throw new UserServiceException(ErrorType.ACCOUNT_STATUS_ERROR);
        }


        user.setStatus(Status.ACTIVE);
        userProfileRepository.save(user);


    }


    public void cacheUserProfile(UserProfile user) {

        redisTemplate.opsForHash().put("userprofiles",user.getUsername(),user);
    }

    public String update(UserProfileUpdateRequest dto) {
        UserProfile user = userProfileRepository.findByAuthId(dto.getAuthId())
                .orElseThrow(() -> new UserServiceException(ErrorType.AUTH_NOT_FOUND));

        if (dto.getPhone() != null) user.setPhone(dto.getPhone());
        if (dto.getAbout() != null) user.setAbout(dto.getAbout());
        if (dto.getPhoto() != null) user.setPhoto(dto.getPhoto());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getAddress() != null) user.setAddress(dto.getAddress());
        if (dto.getStatus() != null) user.setStatus(dto.getStatus());

        userProfileRepository.save(user);
        // Cache'i güncelle
        cacheUserProfile(user);




        return user.getUsername() + " Usernameli user update edildi.";
    }

    @CacheEvict(value = "userprofiles", allEntries = true)
    public String delete(Long authId)
    {
        Optional<UserProfile> user = userProfileRepository.findByAuthId(authId);
        user.get().setStatus(Status.DELETED);
        userProfileRepository.save(user.get());

        return "User Silindi";


    }

    @RabbitListener(queues = "existByAuthId")
    public UserProfileModel existByAuthId(UserProfileModel userProfileModel)
    {

        UserProfile userProfile = userProfileRepository.findByAuthId(userProfileModel.getAuthId()).orElseThrow(() -> new UserServiceException(ErrorType.AUTH_NOT_FOUND));
        UserProfileModel userProfileModel1 = UserProfileModel.builder()
                .authId(userProfile.getAuthId())
                .status(userProfile.getStatus().toString())
                .photo(userProfile.getPhoto())
                .about(userProfile.getAbout())
                .username(userProfile.getUsername())
                .email(userProfile.getEmail())
                .address(userProfile.getAddress())
                .build();

        return userProfileModel1;
    }
    @Cacheable(value = "userprofiles" ,key = "#username")
    public UserProfile findByUsername(String username)
    {
       return userProfileRepository.findByUsernameIgnoreCase(username).orElseThrow(()-> new UserServiceException(ErrorType.USER_NOT_FOUND));

    }

    @Cacheable(value = "userprofiles")
    public List<UserProfile> findAllByStatus(Status status){
        return userProfileRepository.findAllByStatus(status);
    }



    @PostConstruct
    private void init()
    {
        hashOperations = redisTemplate.opsForHash();
    }
}
