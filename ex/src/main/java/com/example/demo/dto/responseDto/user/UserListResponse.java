package com.example.demo.dto.responseDto.user;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListResponse {
	
	List<UserResponse> list = new ArrayList<>();
}
