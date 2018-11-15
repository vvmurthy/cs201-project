package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import utilities.FilledPreferences;
import utilities.ProfileGsonHolder;
import utilities.ProfileInfo;
import utilities.RunUserMatch;
import utilities.SqlDriver;

@ServerEndpoint(value = "/ws")
public class UpdateMatchesSocket {

	private static Vector<Session> sessionVector = new Vector<Session>();
	private static Vector<Integer> UserIds = new Vector<Integer>();
	
	@OnOpen
	public void open(Session session) {
		System.out.println("Connection made!");
		
	}
	
	/**
	 * Updates matches for a session of user with id
	 * otherId
	 * @param s
	 * @param otherId
	 */
	public void updateMatches(Session s, int otherId) throws IOException{
		// Rerun the matching algo, return new results
		FilledPreferences self = SqlDriver.getSelfPreferences(otherId);
		List<ProfileInfo> profiles = RunUserMatch.getMatches(self, otherId);
		
		ProfileGsonHolder pg = new ProfileGsonHolder();
		pg.setProfiles(profiles);
		
		Gson g = new Gson();
		
		String message = g.toJson(profiles);
		s.getBasicRemote().sendText(message);
	}
	
	/**
	 * Get the user's userId (meaning the user just sent it, and is 
	 * now recently only on matches). Get all userIds of the other,
	 * currently existing users 
	 * @param userId
	 * @param session
	 */
	@OnMessage
	public void onMessage(String userId, Session session) {
		System.out.println(userId);
		try {
			for(int i = 0 ; i < sessionVector.size() ; i++) {
				Session s = sessionVector.get(i);
				int otherId = UserIds.get(i);
				updateMatches(s, otherId);
			}
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
			ioe.printStackTrace();
			close(session);
		}
		
		// update matches of message user
		sessionVector.add(session);
		UserIds.add(Integer.parseInt(userId));
		try {
			updateMatches(session, Integer.parseInt(userId));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@OnClose
	public void close(Session session) {
		System.out.println("Disconnecting!");
		int index = sessionVector.indexOf(session);
		sessionVector.remove(session);
		UserIds.remove(index);
	}
	
	@OnError
	public void error(Throwable error) {
		error.printStackTrace();
		System.out.println("Error!");
	}
}

