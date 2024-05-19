package ru.matrosov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.matrosov.model.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    Optional<Person> findByNickname(String nickname);
    Optional<Person> findByEmail(String nickname);
}
