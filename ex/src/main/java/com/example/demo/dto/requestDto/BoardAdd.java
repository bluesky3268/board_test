package com.example.demo.dto.requestDto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardAdd {

	private String title;
	private String content;
	private String pwd;
	private Long userNo;
	private String writer;
	private List<MultipartFile> imgs;
	private MultipartFile file;
}
