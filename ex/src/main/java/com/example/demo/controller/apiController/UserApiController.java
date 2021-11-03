package com.example.demo.controller.apiController;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.requestDto.Login;
import com.example.demo.dto.requestDto.UserJoin;
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
	public int idDuplicateCheck(@RequestBody String id) {
		log.info("id param : {}", id);
		boolean result = userService.idDuplicateCheck(id);
		log.info("result : {}", result);
		if(result == true) {
			// 아이디 사용 가능
			return 1;
		}
		return 0;
	}
	
	@PostMapping("/join")
	public int join(UserJoin userJoin, Model model) {
		int result = 0;
		if(userJoin !=null) { 
			result = userService.join(userJoin);
			model.addAttribute("result", result);
		} 
	 return result;
	}

	@PostMapping("/login")
	public int login(Login login, HttpSession session) {
		int result = userService.login(login, session);
		if(result == 1) {
			session.setAttribute("id", login.getId());
		}
		return result;
	}
	
	@DeleteMapping("/user/{no}")
	public int delete(@PathVariable Long no) {
		return userService.delete(no);
	}
}
