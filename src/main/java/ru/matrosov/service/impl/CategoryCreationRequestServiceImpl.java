package ru.matrosov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrosov.model.CategoryCreationRequest;
import ru.matrosov.repository.CategoryCreationRequestRepository;
import ru.matrosov.service.CategoryCreationRequestService;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryCreationRequestServiceImpl implements CategoryCreationRequestService {
    private final CategoryCreationRequestRepository repository;

    @Override
    public List<CategoryCreationRequest> getAll() {
        var foundRequests = repository.findAll();
        if (foundRequests.isEmpty()) {
            throw new RuntimeException("В таблице не было найдено ни одного запроса на создание категории");
        }
        return foundRequests;
    }

    @Override
    public List<CategoryCreationRequest> getUserRequests(String authorId) {
        var usersRequest = repository.findAllByAuthorId(authorId);
        if (usersRequest.isEmpty()) {
            throw new RuntimeException("Запросы на создание категории не найдены");
        }
        return usersRequest;
    }

    @Transactional
    @Override
    public CategoryCreationRequest create(CategoryCreationRequest request) {
        try {
            return repository.save(request);
        } catch (Exception e) {
            throw new RuntimeException("Во время сохранения запроса на создание категории с id=[%s] в таблицу произошла ошибка: %s"
                    .formatted(request.getId(), e));
        }
    }

    @Transactional
    @Override
    public CategoryCreationRequest update(CategoryCreationRequest requestToUpdate) {
        try {
            return repository.save(requestToUpdate);
        } catch (Exception e) {
            throw new RuntimeException("Во время обновления запроса на создание категории с id=[%s] произошла ошибка: %s"
                    .formatted(requestToUpdate.getId(), e));
        }
    }

    @Transactional
    @Override
    public Boolean delete(String id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Во время удаления запроса на создание категории с id=[%s] произошла ошибка: %s".formatted(id, e));
        }
    }
}
