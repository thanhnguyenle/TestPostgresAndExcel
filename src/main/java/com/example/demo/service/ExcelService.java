package com.example.demo.service;

import com.example.demo.entities.Actor;
import com.example.demo.model.ExcelHelper;
import com.example.demo.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    ExcelHelper excelHelper;

    public void save(MultipartFile file) {
        try {
            List<Actor> tutorials = excelHelper.excelToTutorials(file.getInputStream());
            actorRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<Actor> tutorials = actorRepository.findAll();

        ByteArrayInputStream in = excelHelper.tutorialsToExcel(tutorials);
        return in;
    }
}
