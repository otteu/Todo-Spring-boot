package com.example.todo.repository.search;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.todo.domain.QTodo;
import com.example.todo.domain.Todo;
import com.example.todo.domain.dto.TodoDTO;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;

import dto.PageRequestDTO;
import dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TodoSearchImpl implements TodoSearch {

	private final JPQLQueryFactory queryFactory;
	private final ModelMapper modelMapper;
	
	@Override
	public PageResponseDTO<TodoDTO> search(String keyword, PageRequestDTO pageRequestDTO) 
	{
		Pageable pageable = PageRequest.of(pageRequestDTO.getPage(), pageRequestDTO.getSize());
		QTodo qTodo = QTodo.todo;
		
		JPQLQuery<Todo> query = queryFactory.selectFrom(qTodo)
				.where(qTodo.title.contains(keyword))
				.orderBy(qTodo.tno.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize());
		
		List<Todo> result = query.fetch();
		long totalCount = query.fetchCount();
		
		List<TodoDTO> dtoList = result.stream()
			.map(todo -> modelMapper.map(todo, TodoDTO.class))
			.toList();
		
		PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
				.dtoList(dtoList)
				.pageRequestDTO(pageRequestDTO)
				.totalCount(totalCount)
				.build();
		
		return pageResponseDTO;
	}

}
