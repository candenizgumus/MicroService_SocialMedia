package com.candenizgumus.controllers;


import com.candenizgumus.dto.request.ElasticPostDeleteRequest;
import com.candenizgumus.dto.request.FindAllByPageRequestDto;
import com.candenizgumus.dto.request.PostTweetRequestDto;
import com.candenizgumus.dto.request.UpdatePostTweetRequestDto;
import com.candenizgumus.dto.response.GetAllTweetsResponseDto;
import com.candenizgumus.entity.Post;
import com.candenizgumus.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.candenizgumus.constants.EndPoints.*;

@RequestMapping(POST)
@RestController
@RequiredArgsConstructor
public class PostController
{

    private final PostService postService;


    @PostMapping(SAVE)
    public ResponseEntity<String> postTweet(@RequestBody PostTweetRequestDto dto){

        return ResponseEntity.ok(postService.save(dto));

    }

    @GetMapping(GETMYTWEETS+"/{authId}")
    public List<String> getMyTweets(@PathVariable Long authId){

        return postService.getMyTweets(authId);

    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@RequestBody ElasticPostDeleteRequest dto){
        postService.delete(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getalltweets")
    public List<GetAllTweetsResponseDto> getAllTweetsResponseDto(){
        return postService.getAllTweetsResponseDto();
    }

    @PutMapping("updatetweet")
    public String updateTweet(@RequestBody UpdatePostTweetRequestDto dto){
        return postService.updateTweet(dto);
    }

    @GetMapping("/findallbypage")
    public Page<Post> sendFindAllByPageRequest(FindAllByPageRequestDto dto){
        return (postService.sendFindAllByPageRequest(dto));
    }


}
