package com.example.todo.repository.search;

import com.example.todo.domain.dto.TodoDTO;

import dto.PageRequestDTO;
import dto.PageResponseDTO;

public interface TodoSearch {

	PageResponseDTO<TodoDTO> search(String keyword, PageRequestDTO pageRequest);
	
}
