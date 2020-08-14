package com.atticus.hr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.atticus.hr.domain.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	

	public User findByEmail(String email);
	public User findByName(String name);
	
	
	/*@Modifying
	@Query("update User u set u.enabled=:status")
	public int updateUserStatus(@Param("status") boolean status);*/

}
