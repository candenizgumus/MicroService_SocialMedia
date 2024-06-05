package com.candenizgumus.service;

import com.candenizgumus.domain.Post;
import com.candenizgumus.dto.ElasticPostSaveRequest;
import com.candenizgumus.dto.FindAllByPageRequestDto;
import com.candenizgumus.exceptions.ElasticServiceException;
import com.candenizgumus.exceptions.ErrorType;
import com.candenizgumus.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService
{
    private final PostRepository postRepository;

    @RabbitListener(queues = "elasticpostsave")
    public void save(Post post)
    {
        postRepository.save(post);
    }

    @RabbitListener(queues = "elasticpostdelete")
    public void delete(ElasticPostSaveRequest dto)
    {
        postRepository.deleteById(dto.id());
    }


    @RabbitListener(queues = "findallbypage")
    public Page<Post> handleFindAllByPageRequest(FindAllByPageRequestDto dto) {

            Sort sort = Sort.by(Sort.Direction.fromString(dto.sortDirection()), dto.sortParameter());
            Pageable pageable = PageRequest.of(dto.page(), dto.size(), sort);
            return postRepository.findAll(pageable);

    }


    //TODO LOCALDATE TIME CONVERTING ISLEMINDEN HATA VERIYOR
    public Iterable<Post> findAll()
    {
        return postRepository.findAll();
    }
}
