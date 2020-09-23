package com.tcm.tinder.application;


import java.util.ArrayList;
import java.util.List;

import com.tcm.tinder.application.dto.ProfileDTO;
import com.tcm.tinder.domain.Profile;
import com.tcm.tinder.persistence.ProfileRepository;
import com.tcm.tinder.utilities.InvalidParameterException;


public class ProfileController {

	public ProfileDTO createProfile(ProfileDTO profileDTO) throws Exception {
		Profile profile = new Profile(profileDTO);
		new ProfileRepository().saveProfile(profile);
		
		return new ProfileDTO(profile);
	}

	public void deleteUser(String id) throws Exception {
		new ProfileRepository().deleteUser(id);	
	}

	public ProfileDTO getProfile(String id) throws Exception {
		Profile profile = new ProfileRepository().getProfile(id);
		return new ProfileDTO(profile);
	}

	public void addCandidate(String id1, String id2) throws Exception {
		ProfileDTO profile = this.getProfile(id1);
		Profile p1 = new Profile(profile);
		ProfileDTO profile2 = this.getProfile(id2);
		Profile p2 = new Profile(profile2);
		p1.addCandidate(p2);
		if(p2.isAMatch(p1)) {
			new ProfileRepository().addMatch(id1, id2);
			p1.isAMatch(p2);
		}
	}

	public ProfileDTO getCandidate(String id) throws Exception {
		ProfileDTO p = this.getProfile(id);
		Profile p1 = new Profile(p);
		List<Profile> allProfiles = new ProfileRepository().getAllProfiles();
		for(Profile profile : allProfiles) {
			if(p1.getCompatibility(profile)) return new ProfileDTO(profile);
		}
		return null;
	}

	public List<ProfileDTO> getAllMatches(String id) throws Exception {
		List<Profile> matches = new ProfileRepository().getAllMatches(id);
		
		return convertToDTO(matches);
	}
	
	private List<ProfileDTO> convertToDTO(List<Profile> matches) throws InvalidParameterException {
		List<ProfileDTO> result = new ArrayList<>();
		
		for(Profile profile : matches) {
			result.add(new ProfileDTO(profile));
		}
		return result;
	}
	
	

}
