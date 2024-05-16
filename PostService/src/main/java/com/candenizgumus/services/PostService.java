package com.candenizgumus.services;

import com.candenizgumus.dto.request.PostTweetRequestDto;
import com.candenizgumus.dto.request.UpdatePostTweetRequestDto;
import com.candenizgumus.dto.response.GetAllTweetsResponseDto;
import com.candenizgumus.entity.Post;
import com.candenizgumus.exceptions.ErrorType;
import com.candenizgumus.exceptions.PostServiceException;
import com.candenizgumus.repositories.PostRepository;
import com.candenizgumus.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
                        .username(dto.getUsername())
                        .authId(authId.get())
                .build());
        return "Post işlemi başarılı.";
    }

    public List<String> getMyTweets(Long authId)
    {
        List<Post> userPosts = postRepository.findAllByAuthId(authId);
        List<String> postContexts = new ArrayList<>();

        for (Post userPost : userPosts)
        {
            postContexts.add(userPost.getContext());
        }
        return postContexts;
    }

    public List<GetAllTweetsResponseDto> getAllTweetsResponseDto()
    {
        List<Post> posts = postRepository.findAll();
        List<GetAllTweetsResponseDto> response = new ArrayList<>();
        for (Post post : posts)
        {
            response.add(GetAllTweetsResponseDto.builder().content(post.getContext()).username(post.getUsername()).build());
        }
        return response;
    }

    public String updateTweet(UpdatePostTweetRequestDto dto)
    {
        if (postRepository.existsByAuthIdAndId(dto.getAuthId(), dto.getTweetId()))
        {
            Post post = postRepository.findById(dto.getTweetId()).orElseThrow(()-> new PostServiceException(ErrorType.POST_NOT_FOUND));
            post.setContext(dto.getNewContext());
            postRepository.save(post);
            return "Güncelleme işlemi başarılı  ";
        }

        return "Güncelleme hatası";

    }
}
