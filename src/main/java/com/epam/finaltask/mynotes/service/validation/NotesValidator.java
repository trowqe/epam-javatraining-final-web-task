package com.epam.finaltask.mynotes.service.validation;

import com.epam.finaltask.mynotes.entity.Note;

public class NotesValidator {
	private static final int MIN_TEXT_LENGTH = 3;
	private static final int MAX_TEXT_LENGTH = 65000;

	private static final int MIN_TITLE_LENGTH = 2;
	private static final int MAX_TITLE_LENGTH = 20;

	private static final int MIN_VALID_PICTURE_SIZE = 10; // in bytes
	private static final int MAX_USER_ID = 9090900;

	public static boolean isNoteCorrect(Note note) {
		if (note != null) {
			return isTitleCorrect(note.getTitle()) && isTextCorrect(note.getText())
					&& isPreviewCorrect(note.getPreview());
		}
		return false;
	}

	public static boolean isNoteWithoutPreviewCorrect(Note note) {
		if (note != null) {
			return isTitleCorrect(note.getTitle()) && isTextCorrect(note.getText());
		}
		return false;
	}

	public static boolean isTargetUserCorrect(int idTargetUser) {
		if (idTargetUser > 0 && idTargetUser < MAX_USER_ID) {
			return true;
		}
		return false;
	}

	private static boolean isTitleCorrect(String title) {
		if (title != null && title.length() > MIN_TEXT_LENGTH && title.length() < MAX_TITLE_LENGTH) {
			return true;
		}
		return false;
	}

	private static boolean isTextCorrect(String text) {

		if (text != null && text.length() > MIN_TEXT_LENGTH && text.length() < MAX_TEXT_LENGTH) {
			return true;
		}
		return false;
	}

	private static boolean isPreviewCorrect(byte[] bytes) {
		// no checking for correct img etc
		if (bytes != null) {
			if (bytes.length > MIN_VALID_PICTURE_SIZE) {
				return true;
			}
		}
		return false;
	}
}
