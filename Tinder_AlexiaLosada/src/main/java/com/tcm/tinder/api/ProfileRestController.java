package com.tcm.tinder.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tcm.tinder.application.ProfileController;
import com.tcm.tinder.application.dto.ProfileDTO;


@RestController
public class ProfileRestController {
	
	@PostMapping("/profiles")
	public String createProfile(@RequestBody String jUser) throws Exception {
		Gson gson = new Gson();
		
		ProfileDTO user=gson.fromJson(jUser, ProfileDTO.class);
		
		user = new ProfileController().createProfile(user);
		
		return gson.toJson(user);
	}
	
	@DeleteMapping("/profiles/{id}")
	public void deleteProfile(@PathVariable String id) throws Exception {
		new ProfileController().deleteUser(id);
	}
	
	@GetMapping("/profiles/{id}")
	public String getProfile(@PathVariable String id) throws Exception {
		ProfileDTO user = new ProfileController().getProfile(id);
		Gson gson = new Gson();
		return gson.toJson(user);
	}
	
	@PostMapping("/profiles/{id1}/candidates/{id2}")
	public void addCandidate(@RequestBody String id1, String id2) throws Exception {
		new ProfileController().addCandidate(id1, id2);
	}
	
	
	@GetMapping("/profile/{id}/candidates")
	public String getCandidate(@PathVariable String id) throws Exception{
		ProfileDTO candidate = new ProfileController().getCandidate(id);
		Gson gson = new Gson();
		return gson.toJson(candidate);
	}
	
	@GetMapping("/profile/{id}/matches")
	public String getAllMatches(@PathVariable String id) throws Exception{
		List<ProfileDTO> allMatches = new ProfileController().getAllMatches(id);
		
		Gson gson = new Gson();
		
		return gson.toJson(allMatches);
	}
	
	

}
