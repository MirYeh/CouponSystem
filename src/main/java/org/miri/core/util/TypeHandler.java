package org.miri.core.util;

import org.miri.api.repositories.TypeRepository;
import org.miri.core.beans.Coupon;
import org.miri.core.beans.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handles the {@link Type} entity.
 * @author Miri Yehezkel
 */
@Component
public class TypeHandler {
	
	@Autowired
	private TypeRepository repository;
	
	/**
	 * Ensures a type exists in the database. If requested type is not found, it is created.
	 * @param type type to get
	 * @return the requested Type
	 */
	private Type getType(Type type) {
		Type typeAssist = repository.findByNameIgnoreCase(type.getName());
		if (typeAssist == null)
			typeAssist = repository.save(type);
		return typeAssist;
	}
	
	public Type getType(Coupon coupon) {
		return getType(coupon.getType());
	}

}
