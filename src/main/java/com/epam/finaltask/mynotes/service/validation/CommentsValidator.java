package com.epam.finaltask.mynotes.service.validation;

import com.epam.finaltask.mynotes.entity.Comment;

public class CommentsValidator {

	public static boolean isCorrect(Comment comment) {

		return isCorrect(comment.getText());
	}

	private static boolean isCorrect(String text) {
		if (text != null && text.length() > 0 && text.length() < 1000) {
			return true;
		}

		return false;
	}
}
