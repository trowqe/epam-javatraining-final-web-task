package com.epam.finaltask.mynotes.controller.command;

import com.epam.finaltask.mynotes.controller.command.impl.ChangeLocale;
import com.epam.finaltask.mynotes.controller.command.impl.account.AuthorizationCommand;
import com.epam.finaltask.mynotes.controller.command.impl.account.Logout;
import com.epam.finaltask.mynotes.controller.command.impl.account.Registration;
import com.epam.finaltask.mynotes.controller.command.impl.comment.AddComment;
import com.epam.finaltask.mynotes.controller.command.impl.comment.DeleteComment;
import com.epam.finaltask.mynotes.controller.command.impl.goToPage.*;
import com.epam.finaltask.mynotes.controller.command.impl.note.*;

import javax.servlet.annotation.MultipartConfig;
import java.util.HashMap;
import java.util.Map;


@MultipartConfig
public class CommandProvider {
	private Map<String, Command> commands = new HashMap<>();

	public CommandProvider() {
		
		/* commands with note */
		commands.put("create_note", new CreateNewNote());
		commands.put("delete_note", new DeleteNote());
		commands.put("edit_note", new EditNote());
		commands.put("delete_note", new DeleteNote());
		
		/* commands with comment */
		commands.put("add_comment", new AddComment());
		commands.put("delete_comment",new DeleteComment());
		
		/* commands with note access */
		commands.put("add_user_access_to_note", new AddUserAccessToNote());
		commands.put("edit_user_access_to_note", new EditUserAccessToNote());
		commands.put("show_users_with_access", new ShowUsersWithAccess());
		commands.put("remove_user_from_access_to_note", new RemoveUserFromNote());

		/*commands with user's account */
		commands.put("authorization", new AuthorizationCommand());
		commands.put("registration", new Registration());
		commands.put("logout", new Logout());

		/* page actions*/
		commands.put("change_locale", new ChangeLocale());
		
		/* go to */
		commands.put("go_to_adding_user_access_to_note", new GoToAddingUserToNote());
		commands.put("go_to_editing_users_access_to_note", new GoToEditingUserAccess());
		commands.put("go_to_available_notes", new GoToAvailableNotes());
		commands.put("go_to_comments", new GoToComments());
		commands.put("go_to_created_notes", new GoToCreatedNotes());
		commands.put("go_to_creating_note", new GoToCreatingNote());
		commands.put("go_to_error_page", new GoToErrorPage());
		commands.put("go_to_index", new GoToIndexPage());
		commands.put("go_to_main_page", new GoToMainPage());
		commands.put("go_to_registration_page", new GoToRegistrationCommand());
		commands.put("go_to_updating_note", new GoToUpdateNote());
		commands.put("go_to_shared_notes", new GoToSharedNotes());
		commands.put("go_to_settings", new GoToSettings());
	
	}

	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}
}
