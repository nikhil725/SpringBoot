package com.note.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.note.model.Note;

@Repository
public interface INoteDao extends JpaRepository<Note, Integer>
{

	
	
}
