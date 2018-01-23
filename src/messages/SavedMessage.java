package messages;

public class SavedMessage extends Message{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String recipe;
	public SavedMessage(String userName, String recipe) {
		this.action = "Saved";
		this.userName = userName;
		this.recipe = recipe;
	}
	
}
