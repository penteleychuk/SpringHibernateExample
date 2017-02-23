package com.news.springmvc.service;

import java.util.List;

import com.news.springmvc.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.news.springmvc.dao.EventDao;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao dao;
	
	public Event findById(int id) {
		return dao.findById(id);
	}

	public void saveEvent(Event event) {
		dao.saveEvent(event);
	}

	/*
	 * Since the method is running with Transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateEvent(Event event) {
		Event entity = dao.findById(event.getId());
		if(entity!=null){
			entity.setName(event.getName());
			entity.setCreateDate(event.getCreateDate());
			entity.setDescription(event.getDescription());
		}
	}

	public void deleteEventByName(String name) {
		dao.deleteEventByName(name);
	}
	
	public List<Event> findAllEvents() {
		return dao.findAllEvents();
	}

	public Event findEventByName(String name) {
		return dao.findEventByName(name);
	}

	public boolean isEventNameUnique(Integer id, String name) {
		Event event = findEventByName(name);
		return ( event == null || ((id != null) && (event.getId() == id)));
	}
	
}