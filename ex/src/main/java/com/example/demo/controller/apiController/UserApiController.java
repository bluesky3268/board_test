package com.example.demo.controller.apiController;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.requestDto.Login;
import com.example.demo.dto.requestDto.UserJoin;
import com.example.demo.dto.responseDto.user.UserResponse;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

	private final UserService userService;
	
		
	@PostMapping("/idDuplicateCheck")
	public int idDuplicateCheck(@RequestBody HashMap<String, Object> loginId) {
		log.info("param id : {}", loginId);
		String param = String.valueOf(loginId.get("id"));
		boolean result = userService.idDuplicateCheck(param);
		log.info("result : {}", result);
		if(result == true) {
			// 아이디 사용 가능
			log.info("사용가능한 아이디");
			return 1;
		}
		log.info("동일 아이디 존재");
		return 0;
	}
	
	@PostMapping("/join")
	public int join(UserJoin userJoin, Model model) {
		log.info("join : {}, {}, {}",userJoin.getId(), userJoin.getPwd(), userJoin.getPwdConfirm());
		int result = 0;
		if(userJoin != null) { 
			result = userService.join(userJoin);
			model.addAttribute("result", result);
		} 
	 return result;
	}

	@PostMapping("/login")
	public int login(Login login, HttpSession session) {
		log.info("login id : {}", login.getId());
		try {
			int result = userService.login(login);
			UserResponse loginUser = userService.findById(login.getId());
			// 성공 : 1, 실패 : 0
			if(result == 1) {
				session.setAttribute("user", loginUser);
				log.info("로그인 성공");
				return 1;
			}
			log.info("올바르지 않은 비밀번호");
			return 0;
		}catch(NullPointerException e) {
			e.printStackTrace();
			log.info("해당 아이디 없음");
			return -1;
		}
		
	}
	
	@GetMapping("/logout")
	public int logout(HttpSession session) {
		if(session.getAttribute("user") != null) {
			session.removeAttribute("user");
			// 로그아웃 성공
			log.info("로그아웃 성공");
			return 1;
		}
		// 로그아웃 실패
		log.info("로그아웃 실패, 로그인이 먼저 필요");
		return 0;
	}
	
	@DeleteMapping("/user/{no}")
	public int delete(@PathVariable Long no) {
		return userService.delete(no);
	}
}
