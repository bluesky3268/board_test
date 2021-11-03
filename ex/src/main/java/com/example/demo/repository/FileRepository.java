package com.example.demo.repository;

import com.example.demo.entity.UploadFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileRepository {

    int insert(UploadFile uploadFile);

    int delete(Long no);

    UploadFile findByNo(Long no);

    UploadFile findBySavedName(String name);

}
