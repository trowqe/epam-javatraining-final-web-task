package com.epam.finaltask.mynotes.entity;

import java.io.Serializable;

public class Comment implements Serializable{
	
	private static final long serialVersionUID = 6890585248799701415L;
	
	private int idComment;
	private int idNote;
	private int idUser;
	private String text;
	private String date;
	private String author;
	
	public Comment() {}

	public Comment(int idComment, int idNote, int idUser, String text, String date, String author) {
		super();
		this.idComment = idComment;
		this.idNote = idNote;
		this.idUser = idUser;
		this.text = text;
		this.date = date;
		this.author = author;
	}

	public int getIdComment() {
		return idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public int getIdNote() {
		return idNote;
	}

	public void setIdNote(int idNote) {
		this.idNote = idNote;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + idComment;
		result = prime * result + idNote;
		result = prime * result + idUser;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idComment != other.idComment)
			return false;
		if (idNote != other.idNote)
			return false;
		if (idUser != other.idUser)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", idNote=" + idNote + ", idUser=" + idUser + ", text=" + text
				+ ", date=" + date + ", author=" + author + "]";
	}
	
	
	
	
}
