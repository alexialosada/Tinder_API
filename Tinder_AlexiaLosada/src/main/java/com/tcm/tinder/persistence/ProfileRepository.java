package com.tcm.tinder.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tcm.tinder.application.dto.ProfileDTO;
import com.tcm.tinder.domain.Profile;
import com.tcm.tinder.persistence.ConnectionBBDD;
import com.tcm.tinder.persistence.ConnectionRepository;
import com.tcm.tinder.utilities.InvalidParameterException;

public class ProfileRepository {

	public void saveProfile(Profile profile) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		
		String sql = "Insert into TINDER(ID, EMAIL, NICKNAME, PASSWORD, GENDER, ATRACTION, PASSION) values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, profile.getId());
		pst.setString(2, profile.getEmail());
		pst.setString(3, profile.getNickname());
		pst.setString(4, profile.getPassword());
		pst.setInt(5, profile.getGender());
		pst.setInt(6, profile.getAtraction());
		pst.setInt(7, profile.getPassion());
		
		if(pst.executeUpdate() != 1) {
			throw new InvalidParameterException();
		}
		
	}

	public void deleteUser(String id) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		
		try {
			PreparedStatement st;
			st = connection.prepareStatement("DELETE FROM TINDER WHERE ID = ?");
			st.setString(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidParameterException();
		}
		 
		
	}

	public Profile getProfile(String id) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		
		try {
			String sql = "SELECT * FROM TINDER WHERE ID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				return createProfileFromBBDD(rs);
			}
			
			throw new Exception();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InvalidParameterException();
		}
	}

	private Profile createProfileFromBBDD(ResultSet rs) throws Exception {
		String id = rs.getString("ID");
		String email = rs.getString("EMAIL");
		String nickname = rs.getString("NICKNAME");
		String password = rs.getString("PASSWORD");
		int gender = rs.getInt("GENDER");
		int atraction = rs.getInt("ATRACTION");
		int passion = rs.getInt("PASSION");
		
		
		return new Profile(id, email, nickname, password, gender, atraction, passion);
	}

	public List<Profile> getAllProfiles() throws Exception {
		List<Profile> result = new ArrayList();
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		
		
		String sql = "SELECT * FROM TINDER";
		PreparedStatement pst = connection.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Profile p = this.createProfileFromBBDD(rs);
			result.add(p);
		}
		
		return result;
	}

	public void addMatch(String id1, String id2) throws Exception {
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		
		String sql = "Insert into TINDERMARCH(IDPERSONAL, IDMATCH) values (?, ?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, id1);
		pst.setString(2, id2);
		
		if(pst.executeUpdate() != 1) {
			throw new InvalidParameterException();
		}
		
	}

	public List<Profile> getAllMatches(String id) throws Exception {
		List<Profile> result = new ArrayList<Profile>();
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		
		try {
			String sql = "SELECT * FROM TINDERMATCH JOIN TINDER ON TINDERMATCH.IDPERSONAL=TINDER.ID WHERE ID=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.clearParameters();
			
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String profileId = rs.getString(2);
				result.add(this.getProfile(profileId));
			}
			
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InvalidParameterException();
		}
	}
	
	

	
}
