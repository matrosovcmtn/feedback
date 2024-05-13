package ru.matrosov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.matrosov.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
}
