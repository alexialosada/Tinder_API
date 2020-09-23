package com.tcm.tinder.application.dto;

import java.util.ArrayList;
import java.util.List;

import com.tcm.tinder.domain.Profile;
import com.tcm.tinder.utilities.InvalidParameterException;

public class ProfileDTO {
	
	private String id;
	private String email;
	private String nickname;
	private String password;
	private int gender;
	private int atraction;
	private int passion;
	private List<ProfileDTO>matches = new ArrayList<ProfileDTO>();
	private List<ProfileDTO>candidates = new ArrayList<ProfileDTO>();
	
	public ProfileDTO() {
		
	}
	
	public ProfileDTO(Profile user) throws InvalidParameterException {
		if(user == null) throw new InvalidParameterException();
		
		this.id = user.getId();
		this.email = user.getEmail();
		this.nickname = user.getNickname();
		this.password = user.getPassword();
		this.gender = user.getGender();
		this.atraction = user.getAtraction();
		this.passion = user.getPassion();
		for(Profile match : user.getMatches()) {
			matches.add(new ProfileDTO(match));
		}
		
	}

	public String getId() throws InvalidParameterException {
		if(id.equals("")) throw new InvalidParameterException();
		return id;
	}

	public String getEmail() throws InvalidParameterException {
		if(email.equals("")) throw new InvalidParameterException();
		return email;
	}

	public String getNickname() throws InvalidParameterException {
		if(nickname.equals("")) throw new InvalidParameterException();
		return nickname;
	}

	public String getPassword() throws InvalidParameterException {
		if(password.equals("")) throw new InvalidParameterException();
		return password;
	}

	public int getGender() throws InvalidParameterException {
		if(gender <= 4  && gender > 6) throw new InvalidParameterException();
		return gender;
	}

	public int getAtraction() throws InvalidParameterException {
		if(atraction <= 4  && atraction > 7 && atraction == 6) throw new InvalidParameterException();
		return atraction;
	}

	public int getPassion() throws InvalidParameterException {
		if(passion < 0 && passion > 4) throw new InvalidParameterException();
		return passion;
	}

	public List<Profile> getMatches() throws InvalidParameterException {
		List<Profile> result = new ArrayList();
		for(ProfileDTO match : this.matches){
			result.add(new Profile(match));
		}
		return result;
	}
	
	public List<Profile> getCandidates() throws InvalidParameterException {
		List<Profile> result = new ArrayList();
		for(ProfileDTO candidate : this.candidates){
			result.add(new Profile(candidate));
		}
		return result;
	}
	
	

}
