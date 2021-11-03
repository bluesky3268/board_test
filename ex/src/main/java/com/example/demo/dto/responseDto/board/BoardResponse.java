package com.example.demo.dto.responseDto.board;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponse {

	private Long no;
	private String writer;
	private String title;
	private String content;
	private Long count;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private String fileName;
	
	public BoardResponse(Long no, String writer, String title, String content, Long count, LocalDateTime createdDate,
			LocalDateTime modifiedDate, String fileName) {
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.count = count;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.fileName = fileName;
	}
	
	
	
	
}
