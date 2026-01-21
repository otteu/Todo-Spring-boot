package com.example.todo.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.todo.domain.QTodo;
import com.example.todo.domain.Todo;
import com.querydsl.jpa.JPQLQueryFactory;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class TodoRepositoryTest {

	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private JPQLQueryFactory queryFactory;
	
	
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
	
	@Disabled
	@Test
	void testInsert() {
		for (int i = 1; i <= 100; i++) {
			Todo todo = Todo.builder()
				.title("Title..." + i)
				.dueDate(LocalDate.of(2023,12,31))
				.writer("user00")
				.build();
			
			todoRepository.save(todo);
		}
	}
	
	@Disabled
	@Test
	public void testPaging() {
		// import org.springframework.data.domain.Pageable;
		// 파라미터는 0부터 시작, 사이즈 
		Pageable pageable = PageRequest.of(0,10, Sort.by("tno").descending());
		Page<Todo> result = todoRepository.findAll(pageable);
		log.info(result.getTotalElements());
		result.getContent().stream().forEach(todo -> log.info(todo));
	}

	@Test
	void testSearch1() {
		
		String title = "Title...1";
		Pageable pageable = PageRequest.of(0,10, Sort.by("tno").descending());
		
		Page<Todo> result = todoRepository.findByTitleContaining(title, pageable);
		
		result.stream().forEach(todo -> log.info(todo));
		
	}
	
	@Test
	void testSearch2() {
		String title = "Title...1";
		Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
		
		QTodo qTodo = QTodo.todo;
		
		List<Todo> result = queryFactory.selectFrom(qTodo)
                .where(qTodo.title.contains(title)).fetch();

	    result.forEach(todo -> {
	        System.out.println(todo.getTno() + " : " + todo.getTitle());
	    });

	    assertFalse(result.isEmpty());
	}
	
	
	

}
