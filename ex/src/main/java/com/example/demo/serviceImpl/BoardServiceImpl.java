package com.example.demo.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.requestDto.BoardAdd;
import com.example.demo.dto.responseDto.board.BoardListResponse;
import com.example.demo.dto.responseDto.board.BoardResponse;
import com.example.demo.entity.Board;
import com.example.demo.entity.User;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BoardService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	private final int SIZE=10;
	
	@Override
	public int insert(BoardAdd boardAdd) {
		
		Board board = Board.builder()
				.uno(boardAdd.getUserNo())
				.pwd(boardAdd.getPwd())
				.writer(boardAdd.getWriter())
				.title(boardAdd.getTitle())
				.content(boardAdd.getContent())
				.count(0L)
				.createdDate(LocalDateTime.now())
				.modifiedDate(LocalDateTime.now())
				.build();
		
		return boardRepository.insert(board);
	}

	@Override
	public BoardResponse findByNo(Long no) {
		Board board = boardRepository.findByNo(no);
		BoardResponse response = board.toDto();
		
		User findUser = userRepository.findByNo(board.getUno());
		response.setWriter(findUser.getId());
		return response;
	}

	@Override
	public List<BoardResponse> findAll() {
		List<Board> list = boardRepository.findAll();
		List<BoardResponse> response = new ArrayList<>();
		for(Board board : list) {
			BoardResponse dto = board.toDto();
			User findUser = userRepository.findByNo(board.getUno());
			dto.setWriter(findUser.getId());
			response.add(dto);
		}
		return response;
		
	}
	
	@Override
	public List<BoardResponse> boardPaging(int page) {
		PageHelper.startPage(page, SIZE);
		
		List<BoardResponse> list = new ArrayList<>();
		
		List<Board> result = boardRepository.findAll();
		for(Board board : result) {
			BoardResponse dto = board.toDto();
			list.add(dto);
		}
		
		return list;
	}

	@Override
	public void increaseCount(Long bno) {
		boardRepository.increaseCount(bno);
	}

	@Override
	public int count() {
		return boardRepository.countBoard();
	}

	


	
	
	
}
