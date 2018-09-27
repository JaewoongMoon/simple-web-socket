package testapp;

import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * @author	Jae-Woong Moon(mjw8585@gmail.com)
 * @brief	
 * @Date	2018. 3. 23.	 
 */
public class ChatBot {

	public static void main(String[] args) throws Exception {
		final ChatClientEndpoint clientEndpoint = new ChatClientEndpoint(
				new URI("ws://localhost:8080/jee7-websocket-api/chat"));
		clientEndpoint.addMessageHandler(new ChatClientEndpoint.MessageHandler() {
			
			public void handleMessage(String message) {
				JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
				String userName = jsonObject.getString("user");
				if(!"bot".equals(userName)) {
					clientEndpoint.sendMessage(getMessage("Hello " + userName + ", How are you?"));
				}
				
			}
		});
		
		while(true) {
			clientEndpoint.sendMessage(getMessage("Hi There!"));
			Thread.sleep(30000);
		}
	}
	
	/**
	 * @brief Create a json representation
	 * @param message
	 * @return
	 */
	private static String getMessage(String message) {
		return Json.createObjectBuilder()
				.add("user", "bot")
				.add("message", message)
				.build()
				.toString();
	}
}
