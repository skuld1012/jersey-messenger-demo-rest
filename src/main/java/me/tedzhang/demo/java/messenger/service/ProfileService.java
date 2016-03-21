package me.tedzhang.demo.java.messenger.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.tedzhang.demo.java.messenger.database.DBHandler;
import me.tedzhang.demo.java.messenger.model.Message;
import me.tedzhang.demo.java.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = DBHandler.getProfiles();
	
	public ProfileService() {
		profiles.put("ted", new Profile(1L, "ted", "ted", "zhang"));
	}
	
	public List<Profile> getAllProfiles() {
		return new LinkedList<>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if(profile.getProfileName().isEmpty()) {
			return null;
		} else {
			profiles.put(profile.getProfileName(), profile);
		}
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
