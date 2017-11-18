package org.miri.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.miri.beans.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
	
	Type findByNameIgnoreCase(String typeName);
	
	
}
