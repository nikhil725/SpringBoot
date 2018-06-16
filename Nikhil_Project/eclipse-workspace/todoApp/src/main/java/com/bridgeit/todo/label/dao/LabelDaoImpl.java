package com.bridgeit.todo.label.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgeit.todo.label.model.Label;
import com.bridgeit.todo.user.model.User;

public class LabelDaoImpl implements ILabelDao {

	@Autowired
	private SessionFactory mySessionFactory;

	@Override
	public String createlabel(Label label) {

		Session session = mySessionFactory.getCurrentSession();
		session.save(label);

		return null;
	}

	@Override
	public List<Label> getLabels(User user) {
		Session session = mySessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Label.class);
		criteria.add(Restrictions.eq("user", user));
		List<Label> labels = criteria.list();
		return labels;
	}

	@Override
	public void addLableOnNote(int noteId, int labelId) {
		Session session = mySessionFactory.getCurrentSession();
		Query query = (Query) session.createQuery("insert into NoteLabel(noteId,labelId)");
		query.executeUpdate();
	}

	@Override
	public Label getLabelById(int id) {
		Session session = mySessionFactory.getCurrentSession();
		return (Label) session.get(Label.class, id);
	}

	@Override
	public void deleteLabel(int labelId) {
		Session session = mySessionFactory.getCurrentSession();
		String deleteQuery = "delete from Label where labelId= :labelId";
		Query query = session.createQuery(deleteQuery);
		query.setParameter("labelId", labelId);
		query.executeUpdate();
		// Label label = (Label) session.get(Label.class, labelId);
//		session.delete(label);
//		System.out.println("Record deleted Successfully");
	}

	@Override
	public void deleteLabelOnNote(int labelId) {

		Session session = mySessionFactory.getCurrentSession();

	}

	/*
	 * public List<Label> getLabelsByNoteId(int noteId){ Session session =
	 * mySessionFactory.getCurrentSession(); session.createCriteria(Label.class) }
	 */

}
