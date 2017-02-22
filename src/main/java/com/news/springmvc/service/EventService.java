package com.news.springmvc.service;

import java.util.List;

import com.news.springmvc.model.Event;

public interface EventService {

	Event findById(int id);
	
	void saveEvent(Event event);
	
	void updateEvent(Event event);
	
	void deleteEventByName(String name);

	List<Event> findAllEvents();
	
	Event findEventByName(String name);

	boolean isEventNameUnique(Integer id, String name);
	
}
