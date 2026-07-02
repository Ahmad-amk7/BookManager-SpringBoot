package com.example.demo.Services;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Models.UserAccount;
import com.example.demo.Models.UserStamp;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Repositories.UserStampRepository;

@Service
@Transactional
public class UserStampService implements UserStampFace {
	
	private UserStampRepository _UserStampRepository;
	private UserRepository _UserRepository;
	


	public UserStampService(UserStampRepository _UserStampRepository, UserRepository _UserRepository) {
		super();
		this._UserStampRepository = _UserStampRepository;
		this._UserRepository = _UserRepository;
	}

	@Override
	public void InsertNewStamp(UserStamp userStamp, String id) {
		
		UserAccount account = new UserAccount();
		try 
		{
			account = _UserRepository.findById(id).orElse(null);
			if(account != null)
			{
				userStamp.setStampId(UUID.randomUUID().toString().substring(0, 8));
				userStamp.setUserAccount(account);
				_UserStampRepository.save(userStamp);
			}
			
		} 
		catch (Exception e) {
			System.out.println("the error in InsertNewStamp is = " + e.toString());
		}
		
	}

	@Override
	public void UpdateStamp(UserStamp userStamp, String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeleteStamp(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserStamp SelectStampByUserId(String id) {
		UserAccount account = new UserAccount();
		UserStamp stamp = new UserStamp();
		try {
			account = _UserRepository.findById(id).orElse(null);
			if(account != null)
			{
				stamp = _UserStampRepository.findByUserAccount(account).orElse(null);
				return stamp;
			}
			
		} catch (Exception e) {
			System.out.println("the error in SelectStampByUserId is = " + e.toString());
		}
		
		
		return null;
	}
	
	

}
