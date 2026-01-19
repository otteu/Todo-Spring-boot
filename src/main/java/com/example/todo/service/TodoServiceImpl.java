package com.example.todo.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.domain.Todo;
import com.example.todo.domain.dto.TodoDTO;
import com.example.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService {
	
	private final TodoRepository todoRepository;
	private final ModelMapper modelMapper;
	
	@Override
	public Long register(TodoDTO todoDTO) {
		log.info(".........");
		Todo todo = modelMapper.map(todoDTO, Todo.class);
		Todo saveTodo = todoRepository.save(todo);
		
		return saveTodo.getTno();
	}

	@Override
	public TodoDTO get(Long tno) {
		// TODO Auto-generated method stub
	 	Optional<Todo> result = todoRepository.findById(tno);
	 	Todo todo = result.orElseThrow();
	 	TodoDTO dto = modelMapper.map(todo, TodoDTO.class);
	 	
		return dto;
	}

	@Override
	public void modify(TodoDTO todoDTO) {
		// TODO Auto-generated method stub
		Optional<Todo> result = todoRepository.findById(todoDTO.getTno());
		Todo todo = result.orElseThrow();
		
		todo.changeTitle(todoDTO.getTitle());
		todo.changeComplete(todoDTO.isComplete());
		todo.changeDueDate(todoDTO.getDueDate());
		
		
	}

	@Override
	public void remove(Long tno) {
		// TODO Auto-generated method stub
		
	}
}
