package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.requestDto.Login;
import com.example.demo.dto.requestDto.UserJoin;
import com.example.demo.dto.responseDto.user.UserListResponse;
import com.example.demo.dto.responseDto.user.UserResponse;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping("/")
	public String main() {
		return "redirect:/board";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
	 return "/user/loginForm";
	}
	
	@ResponseBody
	@GetMapping("/find/{no}")
	public UserResponse findUser(@PathVariable Long no) {
	 return userService.findByNo(no);
	}
	
	@ResponseBody
	@GetMapping("/users")
	public UserListResponse users() {
	List<UserResponse> list = userService.findAll();
	UserListResponse response = new UserListResponse();
	for(UserResponse userResponseDto : list) {
		response.getList().add(userResponseDto);
	}

	return response;
	}
	 
}
