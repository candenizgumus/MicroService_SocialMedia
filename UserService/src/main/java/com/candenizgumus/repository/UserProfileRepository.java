package com.candenizgumus.repository;

import com.candenizgumus.entities.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile,String>
{
}
