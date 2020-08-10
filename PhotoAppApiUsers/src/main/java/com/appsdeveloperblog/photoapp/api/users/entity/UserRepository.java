package com.appsdeveloperblog.photoapp.api.users.entity;

import org.springframework.data.repository.CrudRepository;

import com.appsdeveloperblog.photoapp.api.users.entity.UserEntity;


public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	
	UserEntity findByEmail(String Email);

}
