package com.candenizgumus.controller;

import com.candenizgumus.constants.EndPoints;
import com.candenizgumus.domain.Post;
import com.candenizgumus.dto.FindAllByPageRequestDto;
import com.candenizgumus.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndPoints.ELASTIC+"/post")
public class PostController
{
    private final PostService postService;

    @GetMapping("/handleFindAllByPageRequest")
    public Page<Post> handleFindAllByPageRequest(FindAllByPageRequestDto dto) {

        return postService.handleFindAllByPageRequest(dto);

    }

    @GetMapping("/findall")
    public Iterable<Post> findAll() {

        return postService.findAll();

    }

}
