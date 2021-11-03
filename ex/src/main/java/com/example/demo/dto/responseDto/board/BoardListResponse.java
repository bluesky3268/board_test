package com.example.demo.dto.responseDto.board;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardListResponse {

	PageInfo<?> pageInfo;
}
