package com.candenizgumus.manager;

import com.candenizgumus.dto.request.PostTweetRequestDto;
import com.candenizgumus.dto.request.UpdatePostTweetRequestDto;
import com.candenizgumus.dto.request.UserProfileSaveRequestDto;
import com.candenizgumus.dto.request.UserProfileUpdateRequest;
import com.candenizgumus.dto.response.GetAllTweetsResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.candenizgumus.constants.EndPoints.*;

/**
 * Microserviceler arasında iletişimi RestApi üzerinden sağlamak için kullanılacak interface'dir.
 * Burada iletişim kurulacak olan servisin,controller ortamına istek atacağız.
 * Burada 2 parametre kullanmak zorundayız.
 * 1- url: istek atacağımız servisin controler sınıfına erişim adresini yazacağız.
 * 2- name: zorunlu değildir ama aynı ismi taşıyan birdne çok manager olursa hata verir. Ve bu sorunu bulmak kolay olmayabilir.
 * Yani name'i mutlaka unique vermelisiniz.
 */
@FeignClient(url = "http://localhost:9092/post" , name = "postManager")
public interface PostManager
{
    @PostMapping(SAVE)
    ResponseEntity<String> postTweet(@RequestBody PostTweetRequestDto dto);

    @GetMapping(GETMYTWEETS+"/{authId}")
    List<String> getMyTweets(@PathVariable Long authId);

    @GetMapping("/getalltweets")
    List<GetAllTweetsResponseDto> getAllTweetsResponseDto();

    @PutMapping("updatetweet")
    String updateTweet(@RequestBody UpdatePostTweetRequestDto dto);

}
