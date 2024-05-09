package com.candenizgumus.services;

import com.candenizgumus.dto.request.LoginRequestDto;
import com.candenizgumus.dto.request.RegisterRequestDto;
import com.candenizgumus.dto.response.RegisterResponseDto;
import com.candenizgumus.entities.Auth;
import com.candenizgumus.exceptions.AuthServiceException;
import com.candenizgumus.exceptions.ErrorType;
import com.candenizgumus.mapper.AuthMapper;
import com.candenizgumus.repositories.AuthRepository;
import com.candenizgumus.utility.JwtTokenManager;
import com.candenizgumus.utility.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService
{
    private final AuthRepository authRepository;
    private final TokenManager tokenManager;
    private final JwtTokenManager jwtTokenManager;

    public RegisterResponseDto save(RegisterRequestDto dto)
    {
        if (authRepository.existsByUsername(dto.getUsername()))
        {
            throw new AuthServiceException(ErrorType.USERNAME_ALREADY_TAKEN);
        }

        Auth auth = AuthMapper.INSTANCE.registerRequestDtoToAuth(dto);
        Auth savedAuth = authRepository.save(auth);
        RegisterResponseDto registerResponseDto = AuthMapper.INSTANCE.authToRegisterResponseDto(savedAuth);

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
     * @param dto
     * @return
     */
    public String doLogin(LoginRequestDto dto)
    {
        Auth auth = findByUsernameAndPassword(dto.getUsername(), dto.getPassword());

        //return tokenManager.createToken(auth.getId());

        return jwtTokenManager.createToken(auth.getId()).get();
    }

    public List<Auth> findAll(String token)
    {
        Long idFromToken = null;


            idFromToken = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));



        authRepository.findById(idFromToken).orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));

        return authRepository.findAll();


    }
}
