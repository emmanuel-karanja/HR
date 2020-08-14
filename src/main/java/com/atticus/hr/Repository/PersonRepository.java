package com.atticus.hr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atticus.hr.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
    public Person findByEmailIgnoreCase(@Param("email") String email);
}
