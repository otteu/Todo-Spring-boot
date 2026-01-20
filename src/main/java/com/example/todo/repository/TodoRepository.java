package com.example.todo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.domain.Todo;
import com.example.todo.repository.search.TodoSearch;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch
{
	
	Page<Todo> findByTitleContaining(String title, Pageable pageable);
	
}
