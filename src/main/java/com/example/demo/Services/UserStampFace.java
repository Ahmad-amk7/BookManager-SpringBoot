package com.example.demo.Services;

import com.example.demo.Models.UserStamp;

public interface UserStampFace {
	
	void InsertNewStamp(UserStamp userStamp, String id);
	
	void UpdateStamp(UserStamp userStamp, String id);
	
	void DeleteStamp(String id);
	
	UserStamp SelectStampByUserId(String id); 

}
