package com.example.demo.dto.responseDto.board;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.util.Criteria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListResponse {

	private Criteria criteria;
	private List<BoardResponse> list = new ArrayList<>();
}
