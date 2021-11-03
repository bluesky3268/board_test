package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.responseDto.user.UserResponse;
import com.example.demo.entity.User;

@Mapper
public interface UserRepository {

	int insert(User user);
	List<User> findAll();
	User findByNo(Long no);
	User findById(String id);
	int delete(Long no);
}
