package com.example.demo.Data;


import java.time.LocalDateTime;





public class ConfirmAccount {
	
	public static boolean ChickTime(LocalDateTime accountTime, TimeType timeType)
	{
		boolean userhasMoreTime = false;
		LocalDateTime userPlusTime = null;
		LocalDateTime now = LocalDateTime.now();
		
		switch (timeType){
		case getConfirmAccount: 
			userPlusTime = accountTime.plusHours(12);
			break;
		case getChangePassword:
			userPlusTime = accountTime.plusHours(1);
			break;
			
		}
		if(now.isAfter(userPlusTime))
		{
			userhasMoreTime = false;
		}
			
		
		return userhasMoreTime;
	}

}
