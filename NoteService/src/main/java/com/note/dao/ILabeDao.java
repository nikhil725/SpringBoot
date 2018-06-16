package com.note.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.note.model.Label;

@Repository
public interface ILabeDao extends JpaRepository<Label, Integer> {
	

}
