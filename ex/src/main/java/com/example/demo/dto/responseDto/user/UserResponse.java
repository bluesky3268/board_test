package com.example.demo.dto.responseDto.user;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

	private Long no;
	private String id;
	private LocalDateTime regDate;
	
	public UserResponse(Long no, String id, LocalDateTime regDate) {
		this.no = no;
		this.id = id;
		this.regDate = regDate;
	}
	
	
	
}
