package ru.matrosov.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.matrosov.dto.AuthResponse;
import ru.matrosov.dto.PersonDto;
import ru.matrosov.mapper.PersonToDtoMapper;
import ru.matrosov.service.security.AuthService;
import ru.matrosov.service.util.CookieUtilService;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final CookieUtilService cookieUtilService;
    private final PersonToDtoMapper personMapper;

    @PostMapping("/register")
    public ResponseEntity<PersonDto> register(@RequestBody PersonDto personDto) {
        var result = personMapper.toDto(authService.register(personMapper.fromDto(personDto)));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<PersonDto> login(@RequestBody PersonDto personDto) {
        var res = authService.login(personDto);
        var accessJwtCookie = cookieUtilService.createJwtCookie("access_token", res.getAccessToken());
        var refreshJwtCookie = cookieUtilService.createJwtCookie("refresh_token", res.getRefreshToken());
        return ResponseEntity
                .ok()
                .header(SET_COOKIE, accessJwtCookie.toString(), refreshJwtCookie.toString())
                .body(res.getPersonDto());
    }

    @PostMapping("/refresh")
    public ResponseEntity<PersonDto> refresh(HttpServletRequest request) {
        final String refreshJwt = cookieUtilService.extractTokenFromCookie(request, "refresh_token");
        AuthResponse res = authService.refresh(refreshJwt);
        var accessJwtCookie = cookieUtilService.createJwtCookie("access_token", res.getAccessToken());
        var refreshJwtCookie = cookieUtilService.createJwtCookie("refresh_token", res.getRefreshToken());
        return ResponseEntity
                .ok()
                .header(SET_COOKIE, accessJwtCookie.toString(), refreshJwtCookie.toString())
                .body(res.getPersonDto());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        var emptyAccessJwtCookie = cookieUtilService.createJwtCookie("access_token", "", 0);
        var emptyRefreshJwtCookie = cookieUtilService.createJwtCookie("refresh_token", "", 0);
        return ResponseEntity
                .noContent()
                .header(SET_COOKIE, emptyAccessJwtCookie.toString(), emptyRefreshJwtCookie.toString())
                .build();
    }
}
