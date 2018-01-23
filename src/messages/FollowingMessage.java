package messages;


import recipediaClasses.User;

public class FollowingMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;

	public FollowingMessage(String userName) {
		this.action = "Follow";
		this.userName = userName;
	}
}
