package com.candenizgumus.services;

import com.candenizgumus.config.model.UserProfileModel;
import com.candenizgumus.dto.request.*;
import com.candenizgumus.dto.response.GetAllTweetsResponseDto;
import com.candenizgumus.entity.Post;
import com.candenizgumus.exceptions.ErrorType;
import com.candenizgumus.exceptions.PostServiceException;
import com.candenizgumus.repositories.PostRepository;
import com.candenizgumus.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
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
    private final RabbitTemplate rabbitTemplate;

    public String save(PostTweetRequestDto dto)
    {
        Long authId = jwtTokenManager.getIdFromToken(dto.getToken()).orElseThrow(()-> new PostServiceException(ErrorType.AUTH_NOT_FOUND));

        UserProfileModel userProfileModel = (UserProfileModel) (rabbitTemplate.convertSendAndReceive("exchange.direct","Routing.existByAuthId",UserProfileModel.builder().authId(authId).build()));


        postRepository.save(Post
                .builder()
                        .context(dto.getContent())
                        .username(userProfileModel.getUsername())
                        .authId(userProfileModel.getAuthId())
                .build());

        // Elastic'e kaydetme.
        rabbitTemplate.convertAndSend("exchange.direct","Routing.savepost",Post
                .builder()
                .context(dto.getContent())
                .username(userProfileModel.getUsername())
                .authId(userProfileModel.getAuthId())
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
        Post post = null;
        if (postRepository.existsByAuthIdAndId(dto.getAuthId(), dto.getTweetId()))
        {
            post = postRepository.findById(dto.getTweetId()).orElseThrow(()-> new PostServiceException(ErrorType.POST_NOT_FOUND));
            post.setContext(dto.getNewContext());
            postRepository.save(post);
            rabbitTemplate.convertAndSend("exchange.direct","Routing.savepost",post);
            return "Güncelleme işlemi başarılı  ";
        }



        return "Güncelleme hatası";

    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }


    public void delete(ElasticPostDeleteRequest dto)
    {
        postRepository.deleteById(dto.id());
        rabbitTemplate.convertAndSend("exchange.direct","Routing.deletepost",dto);

    }

    public Page<Post> sendFindAllByPageRequest(FindAllByPageRequestDto dto) {
        Page<Post> posts = (Page<Post>) rabbitTemplate.convertSendAndReceive("exchange.direct", "Routing.findallbypage", dto);
        return posts;
    }

}
