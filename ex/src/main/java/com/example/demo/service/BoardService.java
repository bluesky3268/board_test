package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.requestDto.BoardAdd;
import com.example.demo.dto.responseDto.board.BoardListResponse;
import com.example.demo.dto.responseDto.board.BoardResponse;
import com.example.demo.util.Pager;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {

//	int insert(BoardAdd boardAdd);
	int insert(BoardAdd boardAdd, MultipartFile img, MultipartFile file);
	BoardResponse findByNo(Long no);
	List<BoardResponse> findAll();
	List<BoardResponse> boardPaging(int page);
	int count();
	void increaseCount(Long no);
}
