package com.bntu.mynotes.controller.command;

import com.bntu.mynotes.controller.command.impl.account.AuthorizationCommand;
import com.bntu.mynotes.controller.command.impl.account.Logout;
import com.bntu.mynotes.controller.command.impl.account.Registration;
import com.bntu.mynotes.controller.command.impl.goToPage.*;
import com.bntu.mynotes.controller.command.impl.ChangeLocale;
import com.bntu.mynotes.controller.command.impl.GoToAdminPage;

import javax.servlet.annotation.MultipartConfig;
import java.util.HashMap;
import java.util.Map;


@MultipartConfig
public class CommandProvider {
	private Map<String, Command> commands = new HashMap<>();

	public CommandProvider() {

		/*commands with user's account */
		commands.put("authorization", new AuthorizationCommand());
		commands.put("registration", new Registration());
		commands.put("logout", new Logout());

		/* page actions*/
		commands.put("change_locale", new ChangeLocale());
		
		/* go to */
		commands.put("go_to_error_page", new GoToErrorPage());
		commands.put("go_to_index", new GoToIndexPage());
		commands.put("go_to_main_page", new GoToMainPage());
		commands.put("go_to_registration_page", new GoToRegistrationCommand());
		commands.put("go_to_settings", new GoToSettings());
	    commands.put("go_to_admin_page", new GoToAdminPage());
	}

	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}
}
