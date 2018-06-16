package com.note.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.note.model.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer> {

	User findByEmail(String email);
}
