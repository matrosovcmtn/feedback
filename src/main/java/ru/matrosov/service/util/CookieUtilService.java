package ru.matrosov.service.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CookieUtilService {

    public ResponseCookie createJwtCookie(String cookieName, String token) {
        return createJwtCookie(cookieName, token, null);
    }

    public ResponseCookie createJwtCookie(String cookieName, String token, Integer maxAge) {
        int maxAgeProp = maxAge == null ? (cookieName.equals("access_token") ? 86400 : 604800) : 0;
        return ResponseCookie
                .from(cookieName)
                .value(token)
                .sameSite("None")
                .maxAge(Duration.ofSeconds(maxAgeProp))
//                .domain("localhost")
                .path("/")
                .httpOnly(true)
                .secure(true)
                .build();
    }

    public String extractTokenFromCookie(HttpServletRequest request, String tokenName) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null)
            return null;

        for (Cookie cookie : cookies)
            if (cookie.getName().equals(tokenName))
                return cookie.getValue();

        return null;
    }
}
