package com.candenizgumus.controllers;


import com.candenizgumus.dto.request.PostTweetRequestDto;
import com.candenizgumus.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.candenizgumus.constants.EndPoints.POST;
import static com.candenizgumus.constants.EndPoints.SAVE;

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

}
