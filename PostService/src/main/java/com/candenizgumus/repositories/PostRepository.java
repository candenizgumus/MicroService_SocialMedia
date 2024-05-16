package com.candenizgumus.repositories;

import com.candenizgumus.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post,String>
{
    List<Post> findAllByAuthId(Long authId);
    Boolean existsByAuthIdAndId(Long authId, String tweetId);
}
