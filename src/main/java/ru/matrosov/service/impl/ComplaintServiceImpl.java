package ru.matrosov.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.matrosov.model.Complaint;
import ru.matrosov.repository.ComplaintRepository;
import ru.matrosov.service.ComplaintService;

import java.util.List;

@Service
@AllArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepository repository;

    @Override
    public List<Complaint> getAll() {
        var foundComplaints = repository.findAll();
        if (foundComplaints.isEmpty()) {
            throw new RuntimeException("В таблице не было найдено ни одной жалобы");
        }
        return foundComplaints;
    }

    @Override
    public List<Complaint> getUsersComplaints(String authorId) {
        var usersComplaint = repository.findAllByAuthorId(authorId);
        if (usersComplaint.isEmpty()) {
            throw new RuntimeException("Жалобы у пользователя с id=[%s] не найдена.".formatted(authorId));
        }
        return usersComplaint;
    }

    @Transactional
    @Override
    public Complaint create(Complaint complaint) {
        try {
            return repository.save(complaint);
        } catch (Exception e) {
            throw new RuntimeException("Во время сохранения жалобы с id=[%s] в таблицу произошла ошибка: %s".formatted(complaint.getId(), e));
        }
    }

    @Transactional
    @Override
    public Complaint update(Complaint complaintToUpdate) {
        try {
            return repository.save(complaintToUpdate);
        } catch (Exception e) {
            throw new RuntimeException("Во время обновления жалобы с id=[%s] произошла ошибка: %s".formatted(complaintToUpdate.getId(), e));
        }
    }

    @Transactional
    @Override
    public Boolean delete(String id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Во время удаления жалобы с id=[%s] произошла ошибка: %s".formatted(id, e));
        }
    }
}
