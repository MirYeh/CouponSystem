package org.miri.api.repositories;

import java.util.List;

import org.miri.core.beans.clients.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	Company findByUsernameIgnoreCase(String username);
	Company findByUsernameIgnoreCaseAndIdNot(String username, long id);
	Company findByUsernameAndPassword(String username, String password);
	
	@Query("SELECT c.username FROM Company c")
	List<String> findAllUsernames();
	
}
