package com.candenizgumus.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType
{
  USERNAME_ALREADY_TAKEN(5001,"Bu username daha önce kullanılmıştır. Yeniden deneyiniz.", HttpStatus.BAD_REQUEST),
  USERNAME_OR_PASSWORD_WRONG(5002,"Kullanıcı adı veya şifre hatalı", HttpStatus.BAD_REQUEST),
  PASSWORDS_ARE_NOT_SAME(5003,"Sifreler ayni degil", HttpStatus.BAD_REQUEST),
  EMAIL_TAKEN(5004,"Email daha önce alınmis", HttpStatus.NOT_FOUND),
  INVALID_TOKEN(5005,"Token Geçersizdir" ,HttpStatus.BAD_REQUEST ),
  TOKEN_CREATION_FAILED(5006,"Token yaratmada hata meydana geldi" ,HttpStatus.SERVICE_UNAVAILABLE ),
  TOKEN_VERIFY_FAILED(5007,"Token verify edilemedi." ,HttpStatus.SERVICE_UNAVAILABLE ),
  TOKEN_ARGUMENT_NOTVALID(5008,"Token argümanı yanlış formatta" ,HttpStatus.BAD_REQUEST );


  private Integer code;
  private String message;
  private HttpStatus status;
}
