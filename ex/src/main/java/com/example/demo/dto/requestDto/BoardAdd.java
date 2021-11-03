package com.example.demo.dto.requestDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class BoardAdd {

	private String title;
	private String content;
	private String pwd;
	private Long userNo;
	private String writer;
	private List<MultipartFile> imgs;
	private MultipartFile file;
}
