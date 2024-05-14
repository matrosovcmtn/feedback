package ru.matrosov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.matrosov.model.CategoryCreationRequest;

import java.util.List;

@Repository
public interface CategoryCreationRequestRepository extends JpaRepository<CategoryCreationRequest, String> {
    List<CategoryCreationRequest> findAllByAuthorId(String authorId);
}
