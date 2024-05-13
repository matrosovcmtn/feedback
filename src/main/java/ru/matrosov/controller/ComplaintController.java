package ru.matrosov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.matrosov.dto.ComplaintDto;
import ru.matrosov.mapper.ComplaintToDtoMapper;
import ru.matrosov.model.Complaint;
import ru.matrosov.service.ComplaintService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/complaint")
public class ComplaintController {
    private final ComplaintService complaintService;
    private final ComplaintToDtoMapper complaintMapper;

    @PostMapping("/getAll")
    public ResponseEntity<List<ComplaintDto>> getAll() {
        var result = complaintService.getAll().stream().map(complaintMapper::toDto).toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getUsersComplaints/{id}")
    public ResponseEntity<List<ComplaintDto>> getUsersComplaints(@PathVariable("id") String id) {
        var result = complaintMapper.toDto(complaintService.getUsersComplaints(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public ResponseEntity<ComplaintDto> create(@RequestBody ComplaintDto complaintDto) {
        var result = complaintMapper.toDto(complaintService.create(complaintMapper.fromDto(complaintDto)));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    public ResponseEntity<ComplaintDto> update(@RequestBody Complaint complaint) {
        var result = complaintMapper.toDto(complaintService.update(complaint));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        var result = complaintService.delete(id);
        return ResponseEntity.ok(result);
    }
}
