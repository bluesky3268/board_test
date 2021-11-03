package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.dto.responseDto.board.BoardResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Board {

	private Long no;
	private Long uno;
	private String pwd;
	private String writer;
	private String title;
	private String content;
	private Long count;
	private String fileName;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	@Builder
	public Board(Long uno, String pwd, String writer, String title, String content, Long count, String fileName,
			LocalDateTime createdDate, LocalDateTime modifiedDate) {
		this.uno = uno;
		this.pwd = pwd;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.count = count;
		this.fileName = fileName;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	
	public BoardResponse toDto() {
		BoardResponse response = new BoardResponse(
				this.no, this.writer, this.title, this.content, this.count,
				this.createdDate, this.modifiedDate, this.fileName
				);
		
		return response;
	}

	

	
	
}
