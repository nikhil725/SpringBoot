package com.fundoonotes.noteservice;

public interface ICollaboratorService {

	void createCollaborator(String sharedUseremail, int noteId, int userId);
	void removeCollaborator(String sharedUseremail, int noteId);
}
