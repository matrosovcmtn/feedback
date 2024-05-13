package ru.matrosov.service;

import ru.matrosov.model.CategoryCreationRequest;

import java.util.List;

public interface CategoryCreationRequestService {
    List<CategoryCreationRequest> getAll();

    List<CategoryCreationRequest> getUserRequests(String id);

    CategoryCreationRequest create(CategoryCreationRequest model);

    CategoryCreationRequest update(CategoryCreationRequest model);

    Boolean delete(String id);
}
