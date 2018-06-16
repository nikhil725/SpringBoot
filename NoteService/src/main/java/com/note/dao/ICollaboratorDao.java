package com.note.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.note.model.Collaboration;

@Repository
public interface ICollaboratorDao extends JpaRepository<Collaboration, Integer> {

	@Query()
	void removeCollaborator();
}
