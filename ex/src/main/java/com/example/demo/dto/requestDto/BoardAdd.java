package com.example.demo.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardAdd {

	private String title;
	private String content;
	private String pwd;
	private Long userNo;
	private String writer;
}
