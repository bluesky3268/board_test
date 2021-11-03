package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFile {
    private Long no;
    private String originalName;
    private String savedName;

    public UploadFile() {
    }

    public UploadFile(String originalName, String savedName) {
        this.originalName = originalName;
        this.savedName = savedName;
    }
}
