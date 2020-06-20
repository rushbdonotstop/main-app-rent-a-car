package com.example.request.service;

import com.example.request.model.Report;
import com.example.request.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    public Report addReport(Report report) {
        return this.reportRepository.save(report);
    }

    public List<Report> findAll() {
        return this.reportRepository.findAll();
    }

    public Optional<Report> findById(Long id) {
        return this.reportRepository.findById(id);
    }

}
