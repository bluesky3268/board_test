package com.example.demo.controller.apiController;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpSession;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.example.demo.dto.requestDto.BoardAdd;
import com.example.demo.dto.responseDto.board.BoardResponse;
import com.example.demo.service.BoardService;
import com.example.demo.util.FileHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

	private final BoardService boardService;
	private final FileHandler fileHandler;
	
//	@PostMapping("/v1/add")
//	public int boardAdd(BoardAdd boardAdd) {
//		int result = boardService.insert(boardAdd);
//		return result;
//	}

	@PostMapping("/v2/add")
	public int itemAdd(@RequestPart(value="data") BoardAdd boardAdd,
					   @RequestPart(value="img") MultipartFile img,
					   @RequestPart(value="file") MultipartFile file, HttpSession session) {

		log.info("board : {}", boardAdd.toString());
		int result = boardService.insert(boardAdd, img, file);

		return result;
	}
	
	@GetMapping("/img/{imgName}")
    public Resource downloadImage(@PathVariable String imgName) throws MalformedURLException {

        Resource urlResource = boardService.downloadImg(imgName);
        log.info("urlResource : {}", urlResource);

        return urlResource;
    }

    @GetMapping("/upload/file/{fileNo}")
    public ResponseEntity<Resource> attachFile(@PathVariable Long fileNo) throws MalformedURLException {
    	
    	BoardResponse findBoard = boardService.findByNo(fileNo);
    	String savedName = findBoard.getFile().getSavedName();
    	String originalName = findBoard.getFile().getOriginalName();
    	
    	UrlResource urlResource = new UrlResource("file:" + fileHandler.getFullPath(savedName));
    	
        String encodedUploadFileName = UriUtils.encode(originalName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }

}
