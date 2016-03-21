package me.tedzhang.demo.java.messenger.database;

import java.util.HashMap;
import java.util.Map;

import me.tedzhang.demo.java.messenger.model.Message;
import me.tedzhang.demo.java.messenger.model.Profile;

public class DBHandler {

	private static Map<Long, Message> messageMap = new HashMap<>();
	private static Map<String, Profile> profileMap = new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return messageMap;
	}
	
	public static Map<String, Profile> getProfiles() {
		return profileMap;
	}
}
