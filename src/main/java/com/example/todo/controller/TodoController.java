package com.example.todo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.domain.dto.TodoDTO;
import com.example.todo.service.TodoService;

import dto.PageRequestDTO;
import dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;
	
	@GetMapping("/{tno}")
	public TodoDTO get(@PathVariable("tno") Long tno)
	{
		return todoService.get(tno);
	}
	
	@GetMapping("/list")
	public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO)
	{
		return todoService.list(pageRequestDTO);
	}
	
	@PostMapping("/")
	public Map<String, Long> register(@RequestBody TodoDTO todoDTO)
	{
		Long tno = todoService.register(todoDTO);
		
		return Map.of("TNO",tno);
	}
	
	@PutMapping("/{tno}")
	public Map<String, String> modify(@PathVariable("tno") Long tno, @RequestBody TodoDTO todoDTO)
	{
		todoDTO.setTno(tno);
		todoService.modify(todoDTO);
		return Map.of("RESULT", "SUCCESS");
	}

	
	@DeleteMapping("/{tno}")
	public Map<String, String> remove( @PathVariable(name="tno") Long tno )
	{
		todoService.remove(tno);
		return Map.of("RESULT", "SUCCESS");
	}
}
