package com.news.springmvc.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import com.news.springmvc.model.Event;
import com.news.springmvc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	EventService service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing Events.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listEvents(ModelMap model) {

		List<Event> events = service.findAllEvents();
		model.addAttribute("events", events);
		return "allevents";
	}

	/*
	 * This method will provide the medium to add a new Event.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEvent(ModelMap model) {
		Event event = new Event();
		model.addAttribute("event", event);
		model.addAttribute("edit", false);
		return "addnews";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving event in database.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEvent(@Valid Event event, BindingResult result,
							   ModelMap model) {

		if (result.hasErrors()) {
			return "addnews";
		}

		/*
		 * Preferred way to achieve uniqueness of field [description] should be implementing custom @Unique annotation
		 * and applying it on field [description] of Model class [Event].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isEventNameUnique(event.getId(), event.getName())){
			FieldError nameError =new FieldError("event","name",messageSource.getMessage("non.unique.name", new String[]{event.getName()}, Locale.getDefault()));
		    result.addError(nameError);
			return "addnews";
		}
		
		service.saveEvent(event);

		model.addAttribute("success", "Event " + event.getName() + " registered successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing Event.
	 */
	@RequestMapping(value = { "/edit-{name}-event" }, method = RequestMethod.GET)
	public String editEvent(@PathVariable String name, ModelMap model) {
		Event event = service.findEventByName(name);
		model.addAttribute("event", event);
		model.addAttribute("edit", true);
		return "addnews";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating event in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{name}-event" }, method = RequestMethod.POST)
	public String updateEvent(@Valid Event event, BindingResult result,
								 ModelMap model, @PathVariable String name) {

		if (result.hasErrors()) {
			return "addnews";
		}

		if(!service.isEventNameUnique(event.getId(), event.getName())){
			FieldError nameError =new FieldError("event","name",messageSource.getMessage("non.unique.name", new String[]{event.getName()}, Locale.getDefault()));
		    result.addError(nameError);
			return "addnews";
		}

		service.updateEvent(event);

		model.addAttribute("success", "Event " + event.getName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * This method will delete an Event by it's name value.
	 */
	@RequestMapping(value = { "/delete-{name}-event" }, method = RequestMethod.GET)
	public String deleteEvent(@PathVariable String name) {
		service.deleteEventByName(name);
		return "redirect:/list";
	}

}
