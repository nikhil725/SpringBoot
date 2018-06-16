package com.bridgeit.todo.collaborator.service;

import com.bridgeit.todo.collaborator.model.Collaborator;

public interface ICollaboratorService {

	void addCollaborator(String sharedUseremail, int noteId, int userId);
	void removeCollaborator(String sharedUseremail, int noteId);
}