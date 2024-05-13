package com.candenizgumus.manager;

import com.candenizgumus.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.candenizgumus.constants.EndPoints.SAVE;

/**
 * Microserviceler arasında iletişimi RestApi üzerinden sağlamak için kullanılacak interface'dir.
 * Burada iletişim kurulacak olan servisin,controller ortamına istek atacağız.
 * Burada 2 parametre kullanmak zorundayız.
 * 1- url: istek atacağımız servisin controler sınıfına erişim adresini yazacağız.
 * 2- name: zorunlu değildir ama aynı ismi taşıyan birdne çok manager olursa hata verir. Ve bu sorunu bulmak kolay olmayabilir.
 * Yani name'i mutlaka unique vermelisiniz.
 */
@FeignClient(url = "http://localhost:9091/userprofile" , name = "userProfileManager")
public interface UserProfileManager
{

    @PostMapping(SAVE)
    ResponseEntity<Void> save(@RequestBody UserProfileSaveRequestDto dto);

}
