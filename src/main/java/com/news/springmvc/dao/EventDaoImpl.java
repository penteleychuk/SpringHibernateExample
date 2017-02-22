package com.news.springmvc.dao;

import java.util.List;

import com.news.springmvc.model.Event;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("eventDao")
public class EventDaoImpl extends AbstractDao<Integer, Event> implements EventDao {

	public Event findById(int id) {
		return getByKey(id);
	}

	public void saveEvent(Event event) {
		persist(event);
	}

	public void deleteEventByName(String name) {
		Query query = getSession().createSQLQuery("delete from Event where name = :name");
		query.setString("name", name);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Event> findAllEvents() {
		Criteria criteria = createEntityCriteria();
		return (List<Event>) criteria.list();
	}

	public Event findEventByName(String name) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (Event) criteria.uniqueResult();
	}
}
