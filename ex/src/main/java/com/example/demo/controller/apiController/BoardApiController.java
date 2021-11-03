package com.example.demo.controller.apiController;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.requestDto.BoardAdd;
import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

	private final BoardService boardService;
	
//	@PostMapping("/v1/add")
//	public int boardAdd(BoardAdd boardAdd) {
//		int result = boardService.insert(boardAdd);
//		return result;
//	}

	@PostMapping("/v2/add")
	public int itemAdd(@RequestPart(value="data") BoardAdd boardAdd,
					   @RequestPart(value = "img") MultipartFile img,
					   @RequestPart(value="file") MultipartFile file, HttpSession session) {

		int result = boardService.insert(boardAdd, img, file);

		return result;
	}

}
