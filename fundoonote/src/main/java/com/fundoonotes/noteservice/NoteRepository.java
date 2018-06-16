package com.fundoonotes.noteservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fundoonotes.userservice.User;

@Repository(value="noteRepository")
public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query("from Note where user = :user")
	List<Note> findNotesByUserId(@Param("user") User user);
	
	
}