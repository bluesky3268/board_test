package com.example.demo.serviceImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.requestDto.BoardAdd;
import com.example.demo.dto.responseDto.board.BoardListResponse;
import com.example.demo.dto.responseDto.board.BoardResponse;
import com.example.demo.entity.Board;
import com.example.demo.entity.UploadFile;
import com.example.demo.entity.User;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BoardService;
import com.example.demo.service.FileService;
import com.example.demo.util.Criteria;
import com.example.demo.util.FileHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	private final FileHandler fileHandler;
	private final FileService fileService;

	private final int SIZE=10;
	
//	@Override
//	public int insert(BoardAdd boardAdd) {
//
//		Board board = Board.builder()
//				.uno(boardAdd.getUserNo())
//				.pwd(boardAdd.getPwd())
//				.writer(boardAdd.getWriter())
//				.title(boardAdd.getTitle())
//				.content(boardAdd.getContent())
//				.count(0L)
//				.createdDate(LocalDateTime.now())
//				.modifiedDate(LocalDateTime.now())
//				.build();
//
//		return boardRepository.insert(board);
//	}

	@Override
	public int insert(BoardAdd boardAdd, MultipartFile img, MultipartFile multipartFile) {

		log.info("img : {}", img.getOriginalFilename());
		log.info("file : {}", multipartFile.getOriginalFilename());
		
		try {
			UploadFile imgFile = new UploadFile();
			UploadFile file = new UploadFile();

			if (!img.isEmpty() && !multipartFile.isEmpty()) {
				 imgFile = fileHandler.saveFile(img);
				 file = fileHandler.saveFile(multipartFile);
				int imgResult = fileService.insert(imgFile);
				int fileResult = fileService.insert(file);
			} else if (img.isEmpty() && !multipartFile.isEmpty()) {
				file = fileHandler.saveFile(multipartFile);
			} else if (!img.isEmpty() && multipartFile.isEmpty()) {
				imgFile = fileHandler.saveFile(img);
			}
				Board board = Board.builder()
					.uno(boardAdd.getUserNo())
					.pwd(boardAdd.getPwd())
					.writer(boardAdd.getWriter())
					.title(boardAdd.getTitle())
					.content(boardAdd.getContent())
					.count(0L)
					.createdDate(LocalDateTime.now())
					.modifiedDate(LocalDateTime.now())
					.fileName(file.getSavedName())
					.imgName(imgFile.getSavedName())
					.build();

			return boardRepository.insert(board);

		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public BoardResponse findByNo(Long no) {
		Board board = boardRepository.findByNo(no);
		BoardResponse response = board.toDto();

		if (board.getFileName() != null) {
			UploadFile file = fileService.findBySavedId(board.getFileName());
			response.setFile(file);
		}
		if (board.getImgName() != null) {
			UploadFile img = fileService.findBySavedId(board.getImgName());
			response.setImg(img);
		}
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
	public BoardListResponse boardPaging(Criteria criteria) {
		
		int totalCount = boardRepository.countBoard();
	

		List<Board> result = boardRepository.findAllWithPage(criteria);
		BoardListResponse response = new BoardListResponse();
		
		for(Board board : result) {
			BoardResponse dto = board.toDto();
			response.getList().add(dto);
		}
		
		
		response.setCriteria(criteria);
		
		return response;
	}

	@Override
	public void increaseCount(Long bno) {
		boardRepository.increaseCount(bno);
	}


	@Override
	public int count() {
		return boardRepository.countBoard();
	}

	@Override
	public Resource downloadImg(String imgName) throws MalformedURLException {
		UrlResource urlResource = new UrlResource("file:" + fileHandler.getFullPath(imgName));
		return urlResource;
	}


	
	
}
