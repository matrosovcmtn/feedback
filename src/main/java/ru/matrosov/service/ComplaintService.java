package ru.matrosov.service;

import ru.matrosov.model.Complaint;

import java.util.List;

public interface ComplaintService {
    List<Complaint> getAll();

    List<Complaint> getUsersComplaints(String id);

    Complaint create(Complaint model);

    Complaint update(Complaint model);

    Boolean delete(String id);
}
