package com.example.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.example.bean.User;

@Transactional
public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void updateUserById(String countryNAme, Long id) {
		Query query = entityManager.createNativeQuery("UPDATE user_info SET country = ? WHERE id = ?", User.class);
		query.setParameter(1, countryNAme);
		query.setParameter(2, id);
		query.executeUpdate();
	}

}
