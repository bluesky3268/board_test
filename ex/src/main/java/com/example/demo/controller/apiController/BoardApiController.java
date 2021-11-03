package com.example.demo.controller.apiController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.requestDto.BoardAdd;
import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

	private final BoardService boardService;
	
	@PostMapping("/add")
	public int boardAdd(BoardAdd boardAdd) {
		int result = boardService.insert(boardAdd);
		return result;
	}
}
