/*
package com.candenizgumus.config.security;

import com.candenizgumus.exceptions.AuthServiceException;
import com.candenizgumus.exceptions.ErrorType;
import com.candenizgumus.utility.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter
{
    private final JwtTokenManager jwtTokenManager;
    private final JwtUserDetails jwtUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        System.out.println("JWT token filter filtresi devrede");


        //requestte gelen bearer tokeni yakalama
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null)
        {
            String token = bearerToken.substring(7);
            Long authId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));
            System.out.println(authId);

            UserDetails userDetails = jwtUserDetails.loadAuthById(authId);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }


        filterChain.doFilter(request,response);
    }
}
*/
