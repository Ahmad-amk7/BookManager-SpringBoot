package com.example.demo.Services;

import com.example.demo.Models.UserAccount;

public interface UserFace {
	
	void InsertNewUser(UserAccount userAccount);
	void DeleteAccount(String id);
	UserAccount SelectUserByUserEmail(String name);
	UserAccount SelectAccountByUserId(String id);
	void UpdaateAccount(UserAccount account, String id);

}
