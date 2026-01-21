package com.example.todo.service;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.domain.dto.ProductDTO;

import dto.PageRequestDTO;
import dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;


@SpringBootTest
@Log4j2
class ProductServiceTest {

	@Autowired
	private ProductService productService;
	
	
	
	@Test
	public void testList() {
	//1 page, 10 size
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
		PageResponseDTO<ProductDTO> result = productService.getList(pageRequestDTO);
		result.getDtoList().forEach(dto -> log.info(dto));
	}
	
	@Test
	public void testRegister() {
	ProductDTO productDTO = ProductDTO.builder()
		.pname("새로운 상품")
		.pdesc("신규 추가 상품입니다.")
		.price(1000)
		.build();
		//uuid가 있어야 함
		productDTO.setUploadFileNames(
		List.of( UUID.randomUUID()+"_" +"Test1.jpg",
				 UUID.randomUUID()+"_" +"Test2.jpg")
		);
		productService.register(productDTO);
	}


}
