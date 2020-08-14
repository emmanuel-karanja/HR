package com.atticus.hr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atticus.hr.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
