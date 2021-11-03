package com.example.demo.dto.responseDto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

	private Long no;
	private String id;
	private String name;
	
	public UserResponse(Long no, String id, String name) {
		this.no = no;
		this.id = id;
		this.name = name;
	}
	
	
	
}
