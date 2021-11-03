package com.example.demo.serviceImpl;

import com.example.demo.entity.UploadFile;
import com.example.demo.repository.FileRepository;
import com.example.demo.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public UploadFile findBySavedId(String id) {
        return fileRepository.findBySavedName(id);
    }

    @Override
    public int insert(UploadFile uploadFile) {
        int result = fileRepository.insert(uploadFile);
        return result;
    }

    @Override
    public int delete(Long no) {
        return fileRepository.delete(no);
    }
}
