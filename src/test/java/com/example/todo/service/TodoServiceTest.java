package com.example.todo.service;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.domain.Todo;
import com.example.todo.domain.dto.TodoDTO;
import com.example.todo.repository.TodoRepository;

import dto.PageRequestDTO;
import dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class TodoServiceTest {

	@Autowired
	private TodoService todoService;
	
	@Autowired
	private TodoRepository todoRepository;
	
	@BeforeEach
	void setUp()
	{
		for (int i = 1; i <= 10; i++) {
			Todo todo = Todo.builder()
				.title("Title..." + i)
				.dueDate(LocalDate.of(2023,12,31))
				.writer("user00")
				.build();
			
			todoRepository.save(todo);
		}
	}
	
	@Test
	public void testRegister() {
		TodoDTO todoDTO = TodoDTO.builder()
			.title("서비스 테스트")
			.writer("tester")
			.dueDate(LocalDate.of(2023,10,10))
			.build();
		Long tno = todoService.register(todoDTO);
		log.info("TNO: " + tno);
	}

	@Test
	void testRead() {
		Long tno = 1L;
		TodoDTO todoDTO = todoService.get(tno);
		log.info(todoDTO);
	}
	
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
			.page(2)
			.size(10)
			.build();
		
		PageResponseDTO<TodoDTO> response = todoService.list(pageRequestDTO);
		log.info(response);
	}
	
	
	
	
	
	
}
