package com.candenizgumus.services;

import com.candenizgumus.dto.request.*;
import com.candenizgumus.dto.response.GetAllTweetsResponseDto;
import com.candenizgumus.dto.response.RegisterResponseDto;
import com.candenizgumus.entities.Auth;
import com.candenizgumus.enums.Role;
import com.candenizgumus.enums.Status;
import com.candenizgumus.exceptions.AuthServiceException;
import com.candenizgumus.exceptions.ErrorType;
import com.candenizgumus.manager.PostManager;
import com.candenizgumus.manager.UserProfileManager;
import com.candenizgumus.mapper.AuthMapper;
import com.candenizgumus.repositories.AuthRepository;
import com.candenizgumus.utility.CodeGenerator;
import com.candenizgumus.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService
{
    private final AuthRepository authRepository;

    private final JwtTokenManager jwtTokenManager;
    private final UserProfileManager userProfileManager;
    private final PostManager postManager;

    @Transactional
    public RegisterResponseDto save(RegisterRequestDto dto)
    {
        if (authRepository.existsByUsername(dto.getUsername()))
        {
            throw new AuthServiceException(ErrorType.USERNAME_ALREADY_TAKEN);
        }

        Auth auth = AuthMapper.INSTANCE.registerRequestDtoToAuth(dto);
        auth.setActivationCode(CodeGenerator.generateActivationCode());
        Auth savedAuth = authRepository.save(auth);
        RegisterResponseDto registerResponseDto = AuthMapper.INSTANCE.authToRegisterResponseDto(savedAuth);

        UserProfileSaveRequestDto userProfileSaveRequestDto = UserProfileSaveRequestDto
                .builder()
                .username(savedAuth.getUsername())
                .authId(savedAuth.getId())
                .build();
        userProfileManager.save(userProfileSaveRequestDto);

        return registerResponseDto;
    }

    public Auth findByUsernameAndPassword(String username, String password)
    {
        Auth auth = authRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new AuthServiceException(ErrorType.USERNAME_OR_PASSWORD_WRONG));

        return auth;
    }

    /**
     * Username ve password ile doğrulama işlemi yapar.
     * Eğer doğrulama başarısızo lursa hata fırlatır.
     * Eğer doğrulama başarılı ise bir kimlik verelim.
     *
     * @param dto
     * @return
     */
    public String doLogin(LoginRequestDto dto)
    {
        Auth auth = findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.getStatus() != Status.ACTIVE)
        {
            throw  new AuthServiceException(ErrorType.ACCOUNT_IS_NOT_ACTIVE);
        }

        return jwtTokenManager.createTokenFromId(auth.getId()).get();
    }



    public List<Auth> findAll()
    {


        //Long idFromToken = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));


        //authRepository.findById(idFromToken).orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));

        return authRepository.findAll();


    }

    @Transactional
    public String activateAuth(Long authId, String activationCode)
    {
        Auth auth = authRepository.findById(authId).orElseThrow(() -> new AuthServiceException(ErrorType.AUTH_NOT_FOUND));
        if (auth.getStatus() != Status.PENDING)
        {
            throw new AuthServiceException(ErrorType.ACCOUNT_STATUS_ERROR);
        }
        if (!auth.getActivationCode().equals(activationCode))
        {
            throw new AuthServiceException(ErrorType.ACTIVATIONCODE_WRONG);

        }

        auth.setStatus(Status.ACTIVE);
        authRepository.save(auth);

        userProfileManager.activateUser(authId);
        return "Aktivasyon başarılı sisteme girebilirsiniz.";

    }

    public String delete(Long authId)
    {
        Auth auth = authRepository.findById(authId).orElseThrow(() -> new AuthServiceException(ErrorType.AUTH_NOT_FOUND));
        auth.setStatus(Status.DELETED);
        authRepository.save(auth);

        userProfileManager.delete(authId);
        return auth.getId()+"'li kullanıcı silindi.";
    }

    public String getRoleFromToken(String token)
    {
        Optional<String> roleFromToken = jwtTokenManager.getRoleFromToken(token);

        return roleFromToken.get();

    }


    public String getToken(Long id)
    {
        authRepository.findById(id).orElseThrow(()-> new AuthServiceException(ErrorType.AUTH_NOT_FOUND));

        return jwtTokenManager.createTokenFromId(id).get();
    }

    public String getRoleToken(Long id, Role role)
    {
        authRepository.findById(id).orElseThrow(()-> new AuthServiceException(ErrorType.AUTH_NOT_FOUND));

        return jwtTokenManager.createTokenFromIdAndRole(id,role).get();
    }

    public Long getIdFromToken(String token)
    {
        return jwtTokenManager.getIdFromToken(token).get();
    }


    @Transactional
    public String updateUserProfile(UserProfileUpdateRequest dto)
    {
        Optional<Long> authId = jwtTokenManager.getIdFromToken(dto.getToken());
        userProfileManager.update(dto);
        Optional<Auth> auth = authRepository.findById(authId.get());

        if (dto.getEmail()!= null)
        {
            auth.get().setEmail(dto.getEmail());
        }
        if (dto.getStatus() != null)
        {
            auth.get().setStatus(dto.getStatus());
        }



        authRepository.save(auth.get());

        return auth.get().getUsername()+ " adlı authun bilgileri güncellendi.";
    }

    public String postTweet(PostTweetRequestDto dto)
    {
        Long authId = jwtTokenManager.getIdFromToken(dto.getToken()).orElseThrow(()-> new AuthServiceException(ErrorType.AUTH_NOT_FOUND));
        Optional<Auth> auth = authRepository.findById(authId);
        dto.setUsername(auth.get().getUsername());
        postManager.postTweet(dto);

        return "Post işlemi başarılı";
    }

    public List<String> getMyTweets(Long authId)
    {

        return postManager.getMyTweets(authId);
    }

    public List<GetAllTweetsResponseDto> getAllTweets()
    {

        return postManager.getAllTweetsResponseDto();
    }

    public String updateTweet(UpdatePostTweetRequestDto dto)
    {
        return postManager.updateTweet(dto);
    }
}
