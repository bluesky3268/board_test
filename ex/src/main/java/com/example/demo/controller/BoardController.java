package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.responseDto.board.BoardListResponse;
import com.example.demo.dto.responseDto.board.BoardResponse;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;
import com.example.demo.util.Pager;
import com.github.pagehelper.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/")
	public String boardList(@RequestParam(defaultValue="1") int page, Model model) {
		
		BoardListResponse response = new BoardListResponse();
		
		List<BoardResponse> list = boardService.boardPaging(page);

		PageInfo<List> pageInfo = new PageInfo(list);
		response.setPageInfo(pageInfo);
		
		
		model.addAttribute("response", response.getPageInfo().getList());
		model.addAttribute("paging", response.getPageInfo());
		
		return "index";
	}
	
	@ResponseBody
	@GetMapping("/board/{bno}")
	public BoardResponse boardDetail(@PathVariable Long bno, Model model) {
		BoardResponse response = boardService.findByNo(bno);
		log.info("response.getTitle() : {}", response.getTitle());
		model.addAttribute("detail", response);
		boardService.increaseCount(bno);
		return response;
	}
	
}
