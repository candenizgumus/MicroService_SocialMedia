package com.candenizgumus.services;

import com.candenizgumus.dto.request.PostTweetRequestDto;
import com.candenizgumus.entity.Post;
import com.candenizgumus.repositories.PostRepository;
import com.candenizgumus.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService
{
    private final PostRepository postRepository;
    private final JwtTokenManager jwtTokenManager;

    public String save(PostTweetRequestDto dto)
    {
        Optional<Long> authId = jwtTokenManager.getIdFromToken(dto.getToken());
        postRepository.save(Post
                .builder()
                        .context(dto.getContent())
                        .authId(authId.get())
                .build());
        return "Post işlemi başarılı.";
    }
}
