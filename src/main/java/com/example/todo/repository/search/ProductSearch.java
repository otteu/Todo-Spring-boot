package com.example.todo.repository.search;

import com.example.todo.domain.dto.ProductDTO;

import dto.PageRequestDTO;
import dto.PageResponseDTO;



public interface ProductSearch{
	
	PageResponseDTO<ProductDTO> searchList(PageRequestDTO pageRequestDTO);
	
}
