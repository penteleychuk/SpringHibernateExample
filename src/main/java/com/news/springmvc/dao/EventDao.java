package com.news.springmvc.dao;

import java.util.List;

import com.news.springmvc.model.Event;

public interface EventDao {

	Event findById(int id);

	void saveEvent(Event event);
	
	void deleteEventByName(String description);
	
	List<Event> findAllEvents();

	Event findEventByName(String description);

}
