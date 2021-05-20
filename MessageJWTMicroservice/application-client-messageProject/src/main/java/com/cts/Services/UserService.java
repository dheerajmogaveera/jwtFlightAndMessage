package com.cts.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Client.UserHttpClient;
import com.cts.model.JwtRequest;
import com.cts.model.Message;
import com.cts.model.User;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class UserService {
	@Autowired
	private UserHttpClient client;

	public String login(JwtRequest jr) {

		JsonParser parser = new JsonParser();
		String s = client.loginForToken(jr).getBody().toString();
		System.out.println(s);
		JsonElement jsonTree = parser.parse(s);

		JsonObject jsonObject = jsonTree.getAsJsonObject();
		String authorizationHeader = "Bearer " + jsonObject.get("token").getAsString();
		return authorizationHeader;
	}

	public User register(User u) {
		return client.register(u);
	}

	public User getUserByUname(String uname, String token) {
		return client.getUserByUname(token, uname);
	}

	public String inbox(int receiverId, String token) {

		String result = "<ul><li>";
		for (Message m : client.getAllReceivedMessage(token, receiverId)) {
			result += m.getDescription() + "</li><li>";
		}
		result += "</li></ul>";
		System.out.println(result);
		return result;
	}

	public String sentMess(int senderId, String token) {
		String result = "<ul><li>";
		for (Message m : client.getAllSenderMessage(token, senderId)) {
			result += m.getDescription() + "</li><li>";
		}
		result += "</li></ul>";
		System.out.println(result);
		return result;
	}

	public Message sendMessage(Message m, String token) {
		return client.addUserMessage(token, m);
	}

}
