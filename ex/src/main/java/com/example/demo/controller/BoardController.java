package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.responseDto.board.BoardListResponse;
import com.example.demo.dto.responseDto.board.BoardResponse;
import com.example.demo.dto.responseDto.user.UserResponse;
import com.example.demo.service.BoardService;
import com.example.demo.util.Criteria;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	

	@GetMapping("")
	public String boardList(@RequestParam(defaultValue="1") int page, Model model, Criteria criteria) {
		log.info("page param : {}", page);	
		
		criteria.setStartPage(page);
		criteria.setLimit(page);
		int startPage = criteria.getStartPage();
		criteria.setEndPage(startPage);
		
		BoardListResponse response = boardService.boardPaging(criteria);
		model.addAttribute("response", response.getList());
		model.addAttribute("criteria", response.getCriteria());
		
		
		return "index";
	}

	@GetMapping("/addForm")
	public String boardAdd(HttpSession session) {
		UserResponse loginUser = (UserResponse) session.getAttribute("user");
		if (loginUser == null) {
			return "redirect:/loginForm";
		}
		return "board/boardAdd";
	}

	@GetMapping("/{bno}")
	public BoardResponse boardDetail(@PathVariable Long bno, Model model) {
		BoardResponse response = boardService.findByNo(bno);
		log.info("response.getTitle() : {}", response.getTitle());
		model.addAttribute("detail", response);
		boardService.increaseCount(bno);
		return response;
	}
	
}
