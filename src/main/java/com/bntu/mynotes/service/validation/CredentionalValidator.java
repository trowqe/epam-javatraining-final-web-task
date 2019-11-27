package com.bntu.mynotes.service.validation;

public class CredentionalValidator {

	public static boolean isCorrect(String login, String password) {

		return isLoginCorrect(login) && isPasswordCorrect(password);
	}

	public static boolean isCorrect(String login, String password, String email) {

		return isLoginCorrect(login) && isPasswordCorrect(password) && isEmailCorrect(email);
	}

	private static boolean isLoginCorrect(String login) {

		if (login != null && login.length() > 3 && login.length() < 40) {
			return true;
		}
		return false;
	}

	private static boolean isPasswordCorrect(String password) {
		if (password != null && password.length() > 5 && password.length() < 40) {
			return true;
		}
		return false;
	}

	private static boolean isEmailCorrect(String email) {

		if (email != null && email.length() > 3 && email.length() < 40) {
			String emailReg = "^[\\\\w!#$%&�*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&�*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
			return emailReg.matches(email);
		}
		return false;
	}

}
