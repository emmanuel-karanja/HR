package com.atticus.hr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atticus.hr.domain.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, String> {

}
