package ru.matrosov.service.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.matrosov.dto.AuthResponse;
import ru.matrosov.dto.PersonDto;
import ru.matrosov.enumiration.Role;
import ru.matrosov.exception.NoAuthenticationException;
import ru.matrosov.mapper.PersonToDtoMapper;
import ru.matrosov.model.Person;
import ru.matrosov.service.PersonService;

@Service
@AllArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final PersonService personService;
    private final PersonToDtoMapper mapper;

    private Person getUserByJwt(String jwt) {
        try {
            var nickname = jwtService.getUsername(jwt);
            return personService.findByUsername(nickname).orElseThrow(EntityNotFoundException::new);
        } catch (ExpiredJwtException | EntityNotFoundException ignored) {
            throw new NoAuthenticationException();
        }
    }

    public Person register(Person person) {
        var toRegister = new Person();
        toRegister.setNickname(person.getNickname());
        toRegister.setEmail(person.getEmail());
        toRegister.setPassword(person.getPassword());
        toRegister.setRole(Role.USER);

        return personService.create(toRegister);
    }

    public AuthResponse login(PersonDto personDto) {
        var email = personDto.getEmail();
        try {
            personService.getByEmail(email);
        } catch (RuntimeException e) {
            throw new RuntimeException("Пользователь с адресом %s не существует!".formatted(email));
        }
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(email, personDto.getPassword()));
        } catch (BadCredentialsException exception) {
            throw new NoAuthenticationException("Bad credentials");
        }
        Person person = personService.getByEmail(email);
        String accessJwt = jwtService.generateToken(person);
        String refreshJwt = jwtService.generateRefreshToken(person);
        return new AuthResponse(mapper.toDto(person), accessJwt, refreshJwt);
    }

    public AuthResponse refresh(String refreshJwt) {
        try {
            var person = getUserByJwt(refreshJwt);
            var newAccessJwt = jwtService.generateToken(person);
            var newRefreshJwt = jwtService.generateRefreshToken(person);
            return new AuthResponse(mapper.toDto(person), newAccessJwt, newRefreshJwt);
        } catch (IllegalArgumentException ex) {
            throw new NoAuthenticationException();
        }
    }
}
