package com.bridgeit.todo.collaborator.dao;

import java.util.List;

import com.bridgeit.todo.collaborator.model.Collaborator;
import com.bridgeit.todo.notes.model.Notes;
import com.bridgeit.todo.user.model.User;

public interface ICollaboratorDao {

	void addCollaborator(Collaborator collaborator);
	void removeCollaborator(User user, Notes notes);
	List<Collaborator> getCollbySharedId(User sharedUser);
}