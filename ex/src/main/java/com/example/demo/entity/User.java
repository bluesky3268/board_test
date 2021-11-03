package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.dto.responseDto.user.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

	private Long no;
	private String id;
	private String pwd;
	private LocalDateTime regDate;
	
	@Builder
	public User(String id, String pwd, LocalDateTime regDate) {
		this.id = id;
		this.pwd = pwd;
		this.regDate = regDate;
	}
	
	public UserResponse toDto() {
		UserResponse response = new UserResponse(this.no, this.id, this.regDate);
		return response;
	}
}
