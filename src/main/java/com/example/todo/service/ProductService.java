package com.example.todo.service;

import com.example.todo.domain.dto.ProductDTO;

import dto.PageRequestDTO;
import dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ProductService {

	public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);
	
	Long register(ProductDTO productDTO);
	
	ProductDTO get(Long pno);
	
	void modify(ProductDTO productDTO);

	void remove(Long pno);

}
