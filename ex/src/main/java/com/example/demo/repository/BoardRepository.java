package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.Board;
import com.github.pagehelper.Page;

@Mapper
public interface BoardRepository {

	int insert(Board board);

	Board findByNo(Long no);
	
	List<Board> findAll();

	void increaseCount(Long bno);

	int countBoard();
}
