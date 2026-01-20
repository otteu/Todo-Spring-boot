package com.example.todo.service;

import com.example.todo.domain.dto.TodoDTO;

import dto.PageRequestDTO;
import dto.PageResponseDTO;

public interface TodoService {

	Long register(TodoDTO todoDTO);
	
	TodoDTO get(Long tno);
	
	void modify(TodoDTO todoDTO);
	
	void remove(Long tno);
	
	PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
	
}
