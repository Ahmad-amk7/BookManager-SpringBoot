package com.example.demo.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.Models.UserAccount;
import com.example.demo.Repositories.UserRepository;


@Service
@Transactional
public class UserService implements UserFace {

	
	 private UserRepository _UserRepository;
	 
     


	public UserService(UserRepository _UserRepository) {
		super();
		this._UserRepository = _UserRepository;
	}

	@Override
	public void InsertNewUser(UserAccount userAccount) {
		try {
			if(userAccount != null)
			{
				_UserRepository.save(userAccount);
			}
			
		} catch (Exception e) {
			System.out.println("the error in insert new account is = " + e.toString());
		}
		
	}

	@Override
	public void DeleteAccount(String id) {
		UserAccount userAccount = new UserAccount();
		try {
			if(!id.isEmpty() || id != null)
			{
				userAccount = _UserRepository.findById(id).orElse(null);
				_UserRepository.delete(userAccount);
			}
			
		} catch (Exception e) {
			System.out.println("the error in delete account is = " + e.toString());
		}
		
	}


	@Override
	public UserAccount SelectAccountByUserId(String id) {
		UserAccount account = new UserAccount();
		try 
		{
		    account = _UserRepository.findById(id).orElse(null);
			return account;
			
		} catch (Exception e) {
			System.out.println("the error in Select user by userId is = " + e.toString());
		}
		return null;
	}

	@Override
	public void UpdaateAccount(UserAccount account, String id) {
		UserAccount userAccount = new UserAccount();
		try 
		{
			userAccount = _UserRepository.findById(id).orElse(null);
			if(userAccount != null)
			{
				userAccount.setUserName(account.getUserName());
				userAccount.setUserPass(account.getUserPass());
				userAccount.setUserConfirmPass(account.getUserConfirmPass());
				userAccount.setIsActive(account.isIsActive());
				_UserRepository.save(userAccount);
			}
			
		} catch (Exception e) {
			System.out.println("the error in Select user by userId is = " + e.toString());
		}
		
	}

	@Override
	public UserAccount SelectUserByUserEmail(String name) {
		UserAccount account = new UserAccount();
		try {
			if(!name.isEmpty() || name != null)
			{
				account = _UserRepository.findByUserName(name).orElse(null);
			}
			
			
		} catch (Exception e) {
			System.out.println("the error in Select user by Name is = " + e.toString());
		}
		return account;
	}


	

}
