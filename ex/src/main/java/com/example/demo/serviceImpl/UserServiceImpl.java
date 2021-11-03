package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.requestDto.Login;
import com.example.demo.dto.requestDto.UserJoin;
import com.example.demo.dto.responseDto.user.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public boolean idDuplicateCheck(String id) {
		try {
			User findUser = userRepository.findById(id);
			log.info("findUser id : {}", findUser.getId());
		}catch(NullPointerException e) {
			return false;
		}
		return true;
	}
	
	@Override
	public int join(UserJoin userJoin) {
	
		String encodedPwd = passwordEncoder.encode(userJoin.getPwd());
		User user = User.builder()
				.id(userJoin.getId())
				.pwd(encodedPwd)
				.regDate(LocalDateTime.now())
				.build();
		return userRepository.insert(user);

	}

	@Override
	public List<UserResponse> findAll() {
		List<User> list = userRepository.findAll();
		List<UserResponse> dtoList = new ArrayList<>();
		for(User user : list) {
			dtoList.add(user.toDto());
			log.info("userId : {}", user.getId());
		}
		return dtoList;
	}

	@Override
	public UserResponse findByNo(Long no) {
		User findUser = userRepository.findByNo(no);
		UserResponse responseDto = findUser.toDto();
		return responseDto;
	}

	@Override
	public int login(Login login, HttpSession session) {
		User findUser = userRepository.findById(login.getId());
		if(passwordEncoder.matches(login.getPassword(), findUser.getPwd())) {
			session.setAttribute("id", findUser.getId());
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(Long no) {
		int result = userRepository.delete(no);
		return result;
	}



	

}
