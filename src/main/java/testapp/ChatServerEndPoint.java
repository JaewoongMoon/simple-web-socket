package testapp;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author	Jae-Woong Moon(mjw8585@gmail.com)
 * @brief	
 * @Date	2018. 3. 23.	 
 */
@ServerEndpoint(value="/chat")
@Singleton
public class ChatServerEndPoint {

	Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session userSession) {
		System.out.println("New request received. Id: " + userSession.getId());
		userSessions.add(userSession);
	}
	
	@OnClose
	public void onClose(Session userSession) {
		System.out.println("Connection closed. Id:" + userSession.getId());
		userSessions.remove(userSession);
	}
	
	@OnMessage
	/**
	 * @brief 
	 */
	public void onMessage() {

	}
}
