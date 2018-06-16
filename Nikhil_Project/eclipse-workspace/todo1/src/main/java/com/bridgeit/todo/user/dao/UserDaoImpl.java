package com.bridgeit.todo.user.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.todo.user.model.User;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory mySessionFactory;

	public int addUser(User user) {
		System.out.println("In side add user dao");
		Session session = mySessionFactory.getCurrentSession();
		// user's state - transient
		int id  = (Integer)session.save(user);
		
		// user's state - persistent
		
		
		return id;
	}

	public User validateUser(User user) {
		System.out.println("In validate user ");
		Session session = mySessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);

		Criterion email = Restrictions.eq("email", user.getEmail());
		Criterion password = Restrictions.eq("password", user.getPassword());
		Criterion criterion = Restrictions.and(email, password);
		criteria.add(criterion);

		User user1 = (User) criteria.uniqueResult();
		System.out.println(user1.getName()+"..."+user1.getPassword()+"..."+user1.getEmail());
		return user1;
	}

	
	public User getUserByEmaiId(String email) {

		System.out.println("id "+ email);
		System.out.println("before session");
		Session session = mySessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(User.class);
		
		criteria.add(Restrictions.eq("email", email));
		User user2 = (User) criteria.uniqueResult();
		System.out.println("Successfull");

		return user2;

		/*	Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", id));*/	
		//		List<User> list = criteria.list();
		/*String hql = "FROM User u WHERE u.email = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
		List<User> user = query.list();*/
	}

	
	public User getUserById(int id) {

		System.out.println("id "+id);

		Session session = mySessionFactory.getCurrentSession();
		System.out.println("after session");
		User user = (User) session.get(User.class, id);
		System.out.println("in dao "+user.getPassword());
		System.out.println("Successfull....");
		return  user;
	}

	
	public User getUserByRandomId(String randomId) {

		System.out.println("id "+randomId);

		Session session = mySessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("randomId", randomId));
		User user = (User) criteria.uniqueResult();
		
		//session.close();
		System.out.println("result found...");
		
		//User user = (User) session.get(User.class, randomId);

		return user;
	}
	
	
	
	public List<User> checkEmailId(String email) {
		Session session = mySessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		List<User> userList = criteria.list();
		return userList;
	}

	public User updateRecord(User user) {
		
		System.out.println("In side update Record dao");
		Session session = mySessionFactory.getCurrentSession();
		
		// Not required as Spring @transaction is defined
		//org.hibernate.Transaction tx = session.beginTransaction();
		//tx.begin();
		
		session.update(user);
		
		//session.close();
		//tx.commit();
		System.out.println("Record updated...");
		return user;
		
	}



}
