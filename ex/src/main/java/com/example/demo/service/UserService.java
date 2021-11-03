package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.dto.requestDto.Login;
import com.example.demo.dto.requestDto.UserJoin;
import com.example.demo.dto.responseDto.user.UserResponse;

public interface UserService {

	int join(UserJoin userJoin);
	List<UserResponse> findAll();
	UserResponse findByNo(Long no);
	UserResponse findById(String id);
	int login(Login login);
	boolean idDuplicateCheck(String id);
	int delete(Long no);
}
