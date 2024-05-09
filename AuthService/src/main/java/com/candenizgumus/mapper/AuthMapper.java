package com.candenizgumus.mapper;


import com.candenizgumus.dto.request.RegisterRequestDto;
import com.candenizgumus.dto.response.LoginResponseDto;
import com.candenizgumus.dto.response.RegisterResponseDto;
import com.candenizgumus.entities.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper
{
    AuthMapper INSTANCE = Mappers.getMapper( AuthMapper.class );
    Auth registerRequestDtoToAuth(RegisterRequestDto dto);
    RegisterResponseDto authToRegisterResponseDto(Auth auth);
    LoginResponseDto authToLoginResponseDto(Auth auth);



}
