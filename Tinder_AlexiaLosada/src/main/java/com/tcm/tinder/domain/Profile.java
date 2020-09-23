package com.tcm.tinder.domain;

import java.util.ArrayList;
import java.util.List;

import com.tcm.tinder.application.dto.ProfileDTO;
import com.tcm.tinder.utilities.*;

public class Profile {
	
	private String id;
	private String email;
	private String nickname;
	private String password;
	private int gender;
	private int atraction;
	private int passion;
	private List<Profile> matches = new ArrayList<Profile>();
	private List<Profile> candidates = new ArrayList<Profile>();
	
	public Profile() {
		
	}
	
	public Profile(ProfileDTO user) throws InvalidParameterException {
		if(user == null) throw new InvalidParameterException();
		
		this.id = user.getId();
		this.email = user.getEmail();
		this.nickname = user.getNickname();
		this.password = user.getPassword();
		this.gender = user.getGender();
		this.atraction = user.getAtraction();
		this.passion = user.getPassion();
		this.matches = user.getMatches();
		this.candidates = user.getCandidates();
		
	}
	
	public Profile(String id, String email, String nickname, String password, int gender, int atraction, int passion) throws InvalidParameterException {
		if(id.equals("")) throw new InvalidParameterException();
		if(email.equals("")) throw new InvalidParameterException();
		if(nickname.equals("")) throw new InvalidParameterException();
		if(password.equals("")) throw new InvalidParameterException();
		if(passion < 0 && passion > 4) throw new InvalidParameterException();
		if(gender <= 4  && gender > 6) throw new InvalidParameterException();
		if(atraction <= 4  && atraction > 7 && atraction == 6) throw new InvalidParameterException();
		
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.gender = gender;
		this.atraction = atraction;
		this.passion = passion;
		
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public int getGender() {
		return gender;
	}

	public int getAtraction() {
		return atraction;
	}

	public int getPassion() {
		return passion;
	}
	
	public List<Profile> getMatches() {
		return this.matches;
	}
	
	public List<Profile> getCandidates() {
		return this.candidates;
	}
	
	public void addCandidate(Profile p) {
		this.candidates.add(p);
	}
	
	public boolean getCompatibility(Profile user) {
		if(this.attracted(user)) {
			if(user.getPassion() == this.passion) return true;
		}
		return false;
		
	}
	
	private boolean attracted(Profile user) {
		if(user.getGender() == this.atraction) return true;
		if(this.atraction == ConstantUtilities.BISEXUAL) return true;
		return false;
	}

	

	public boolean isAMatch(Profile profile) {
		if(this.candidates.contains(profile)) {
			this.matches.add(profile);
			return true;
		}
		return false;
	}
	
	
	

}
