package messages;

public class LikeUploadedMessage extends Message{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String recipe;
	public LikeUploadedMessage(String userName, String recipe) {
		this.action = "LikeUploaded";
		this.userName = userName;
	}
}
