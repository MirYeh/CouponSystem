package org.miri.api.repositories;

import org.miri.beans.clients.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Customer findByUsernameIgnoreCaseAndIdNot(String username, long id);
	Customer findByUsernameAndPassword(String username, String password);
	
	
	
}
