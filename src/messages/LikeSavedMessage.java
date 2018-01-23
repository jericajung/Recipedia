package messages;

import recipediaClasses.Recipe;
import recipediaClasses.User;

public class LikeSavedMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String recipe;
	public LikeSavedMessage(String userName, String recipe) {
		this.action = "LikeSaved";
		this.userName = userName;
	}

}
