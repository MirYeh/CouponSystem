package org.miri.api.repositories;

import org.miri.core.beans.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
	
	Type findByNameIgnoreCase(String typeName);
	
	
}
