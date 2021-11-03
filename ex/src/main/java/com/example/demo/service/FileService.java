package com.example.demo.service;

import com.example.demo.entity.UploadFile;

public interface FileService {
    int insert(UploadFile uploadFile);
    int delete(Long no);

    UploadFile findBySavedId(String id);
}
