package com.candenizgumus.mapper;


import com.candenizgumus.dto.request.UserProfileSaveRequestDto;
import com.candenizgumus.entities.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper
{
    UserProfileMapper INSTANCE = Mappers.getMapper( UserProfileMapper.class );

    UserProfile dtoToUserProfile(UserProfileSaveRequestDto dto);




}
