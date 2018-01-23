package recipediaClasses;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;



@ServerEndpoint(value = "/ws")

public class WebSocketEndpoint {
	private static final Logger logger = Logger.getLogger("BotEndpoint");
	private static final Map<String, Session> sessions = new HashMap<String, Session>();
	private static final Map<String, String> users = new HashMap<>();
	private static final Map<String, String> usernameToSessionID = new HashMap<>();
	private static Lock lock = new ReentrantLock();

	@OnOpen
	public void open(Session session) {
		lock.lock();
		System.out.println("Connection opened " + session.getId());
		logger.log(Level.INFO, "Connection opened. (id:)" + session.getId());
		sessions.put(session.getId(), session);
		lock.unlock();
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		lock.lock();
		String user = users.get(session.getId());
		
			// factory not yet created, use message as text file
			InputStream is = new ByteArrayInputStream(message.getBytes());
			//User newUser = new User();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			try {
				String username = br.readLine();
				//System.out.println(message);
				usernameToSessionID.put(message, session.getId());
				users.put(session.getId(), message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		lock.unlock();
	}

	@OnClose
	public void close(Session session) {
		lock.lock();
		logger.log(Level.INFO, "Connection closed. (id:)" + session.getId());
		
		lock.unlock();
	}

	@OnError
	public void onError(Throwable error) {
		error.printStackTrace();
	}

	public void sendToSession(Session session, String message) {
		lock.lock();
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException ex) {
			sessions.remove(session.getId());
			logger.log(Level.SEVERE, ex.getMessage(), ex.getStackTrace());
		}
		lock.unlock();
	}
	
	public void sendToUser(String username, String message) {
		lock.lock();
		try {
			System.out.println(usernameToSessionID.size());
			System.out.println(usernameToSessionID.get(username));
			System.out.println(username);
			System.out.println("sending message");
			if (usernameToSessionID.containsKey(username) && sessions.containsKey(usernameToSessionID.get(username))) {
				sessions.get(usernameToSessionID.get(username)).getBasicRemote().sendText(message);
				System.out.println("sent");
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
