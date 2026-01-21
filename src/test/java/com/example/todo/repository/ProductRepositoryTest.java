package com.example.todo.repository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.example.todo.domain.Product;

import dto.PageRequestDTO;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;


@SpringBootTest
@Log4j2
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	
	@BeforeEach
	void setUp()
	{

		for(int i = 0; i < 10; i++)
		{
			Product product = Product.builder()
					.pname("Test" + i)
					.pdesc("Test desc")
					.price(1000)
					.build();
			
			product.addImageString(UUID.randomUUID()+"_"+"IMAGE1.png");
			
			product.addImageString(UUID.randomUUID()+"_"+"IMAGE2.png");
			
			productRepository.save(product);	
		}
		
	}
	
	@Disabled
	@Test
	void testInsert() 
	{
		
		Product product = Product.builder()
				.pname("Test")
				.pdesc("Test desc")
				.price(1000)
				.build();
		
		product.addImageString(UUID.randomUUID()+"_"+"IMAGE1.png");
		
		product.addImageString(UUID.randomUUID()+"_"+"IMAGE2.png");
		
		productRepository.save(product);
		
	}

	@Test
	public void testRead2()
	{	
		Long pno = 1L;
		
		Optional<Product> result = productRepository.selectOne(pno);
		Product productResult = result.orElseThrow();
		
		log.info(productResult);
		log.info(productResult.getImageList());
	}
	
	@Commit
	@Transactional
	@Test
	public void testDel()
	{	
		Long pno = 1L;
		
		productRepository.updateDelete(pno, true);
		
	}
	
	@Test
	public void testUpdate()
	{
		Product product = productRepository.selectOne(1L).get();
		
		product.changePrice(3000);
		
		product.clearList();
		
		product.addImageString(UUID.randomUUID()+"_"+"PIMAGE1.png");
		
		product.addImageString(UUID.randomUUID()+"_"+"PIMAGE2.png");
		
		product.addImageString(UUID.randomUUID()+"_"+"PIMAGE3.png");
		
		productRepository.save(product);
		
	}
	
	
	@Test
	public void testList() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
		Page<Object[]> result = productRepository.selectList(pageable);
		result.getContent().forEach(arr -> log.info(Arrays.toString(arr)));
	}
	
	@Test
	void testSearch()
	{
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
		
		productRepository.searchList(pageRequestDTO);
	}
	
	
}
