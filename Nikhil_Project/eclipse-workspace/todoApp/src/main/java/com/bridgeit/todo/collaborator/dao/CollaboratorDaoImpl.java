package com.bridgeit.todo.collaborator.dao;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgeit.todo.collaborator.model.Collaborator;
import com.bridgeit.todo.notes.model.Notes;
import com.bridgeit.todo.user.model.User;

public class CollaboratorDaoImpl implements ICollaboratorDao{

	
	@Autowired
	private SessionFactory mySessionFactory;
	
	@Override
	public void addCollaborator(Collaborator collaborator) {
		
	Session session = mySessionFactory.getCurrentSession();
	session.save(collaborator);
	System.out.println("collaborat successfully");	
	
	}

	@Override
	public void removeCollaborator(User user, Notes notes) {
				
		Session session = mySessionFactory.getCurrentSession();
		String hql = "delete from Collaborator c where c.noteId = :note and c.sharedUserId = :sharedUser";
		Query query = session.createQuery(hql); 
		query.setParameter("note", notes);
		query.setParameter("sharedUser", user);
		query.executeUpdate();
		System.out.println("record deleted... ");
		
	}

	@Override
	public List<Collaborator> getCollbySharedId(User sharedUser) {
		Session session = mySessionFactory.getCurrentSession();
		String hql = "from Collaborator where sharedUserId = :sharedUserId";
		Query query = session.createQuery(hql);
		query.setParameter("sharedUserId", sharedUser);
		List<Collaborator> coll = query.list();
		return coll;
	}
}