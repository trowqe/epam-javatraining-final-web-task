package com.epam.finaltask.mynotes.service;

import com.epam.finaltask.mynotes.service.impl.ClientServiceImpl;
import com.epam.finaltask.mynotes.service.impl.CommentServiceImpl;
import com.epam.finaltask.mynotes.service.impl.NotesServiceImpl;

public class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider();
	
	private ServiceProvider() {}
	
	private ClientService clientService = new ClientServiceImpl();
	private NotesService notesService = new NotesServiceImpl();
	private CommentService commentService = new CommentServiceImpl();
	
	public ClientService getClientService() {
		return clientService;
	}

	public NotesService getNotesService() {
		return notesService;
	}
	
	public CommentService getCommentService() {
		return commentService;
	}
	
	public static ServiceProvider getInstance() {
		return instance;
	}
	
}
