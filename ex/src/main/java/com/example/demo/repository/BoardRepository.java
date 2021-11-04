package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Board;
import com.example.demo.util.Criteria;

@Mapper
public interface BoardRepository {

	int insert(Board board);

	Board findByNo(Long no);
	
	List<Board> findAll();
	
	List<Board> findAllWithPage(Criteria criteria);

	void increaseCount(Long bno);

	int countBoard();
}
