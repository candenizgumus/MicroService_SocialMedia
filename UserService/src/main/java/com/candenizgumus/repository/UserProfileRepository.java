package com.candenizgumus.repository;

import com.candenizgumus.entities.UserProfile;
import com.candenizgumus.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile,String>
{

    Optional<UserProfile> findByAuthId(Long authId);

    Optional<UserProfile> findByUsernameIgnoreCase(String username);
    List<UserProfile> findAllByStatus(Status status);
}
