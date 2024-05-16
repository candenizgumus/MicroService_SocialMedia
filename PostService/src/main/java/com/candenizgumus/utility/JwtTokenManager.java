package com.candenizgumus.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.candenizgumus.exceptions.ErrorType;
import com.candenizgumus.exceptions.PostServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager
{

    @Value("${authservice.secret.secret-key}")
    String secretKey ;
    @Value("${authservice.secret.issuer}")
    String issuer ;
    Long expireTime = 1000L * 60*15; //15 dakikalık zaman


    public Optional<Long> getIdFromToken(String token)
    {



        try
        {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT==null)
            {
                return Optional.empty();

            }


            return Optional.of(decodedJWT.getClaim("id").asLong());
        } catch (IllegalArgumentException e)
        {

            throw new PostServiceException(ErrorType.TOKEN_ARGUMENT_NOTVALID);
        } catch (JWTVerificationException e)
        {
            throw new PostServiceException(ErrorType.TOKEN_VERIFY_FAILED);
        }


    }

    public Optional<String> getRoleFromToken(String token)
    {



        try
        {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT==null)
            {
                return Optional.empty();

            }


            return Optional.of(decodedJWT.getClaim("role").asString());
        } catch (IllegalArgumentException e)
        {

            throw new PostServiceException(ErrorType.TOKEN_ARGUMENT_NOTVALID);
        } catch (JWTVerificationException e)
        {
            throw new PostServiceException(ErrorType.TOKEN_VERIFY_FAILED);
        }


    }




}
