package com.example.demo.service;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.requestDto.BoardAdd;
import com.example.demo.dto.responseDto.board.BoardListResponse;
import com.example.demo.dto.responseDto.board.BoardResponse;
import com.example.demo.util.Criteria;

public interface BoardService {

//	int insert(BoardAdd boardAdd);
	
	int insert(BoardAdd boardAdd, MultipartFile img, MultipartFile file);
	
	BoardResponse findByNo(Long no);
	
	List<BoardResponse> findAll();
	
	BoardListResponse boardPaging(Criteria criteria);
	
	int count();
	
	void increaseCount(Long no);
	
	Resource downloadImg(String imgName) throws MalformedURLException;
}
