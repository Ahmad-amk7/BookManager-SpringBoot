package com.example.demo.Data;

import java.security.MessageDigest;

public class MDHash {
	
	public static String getHashText(String strKey, String strSalt1, String strSalt2, String strSalt3, String strSalt4)
	{
		StringBuilder builder = new StringBuilder();
		StringBuilder finaBuilder = new StringBuilder();
		String passPart1 = "";
		String passPart2 = "";
		String passPart3 = "";
		
		for(int i = 0; i < strKey.length(); i++)
		{
			passPart1 += strKey.charAt(i);
			if(i == 3)
				break;
		}
		for(int i = 4; i < strKey.length(); i++)
		{
			passPart2 += strKey.charAt(i);
			if(i == 6)
				break;
		}
		for(int i = 7; i < strKey.length(); i++)
		{
			passPart3 += strKey.charAt(i);
		}
		
		builder.append(strSalt1)
		.append(passPart1)
		.append(strSalt2)
		.append(passPart2)
		.append(strSalt3)
		.append(passPart3)
		.append(strSalt4)
		.toString();
		
		try 
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(builder.toString().getBytes(StandardCharsets.UTF_8));

            StringBuilder finalBuilder = new StringBuilder();
           for (byte b : hash) {
             finalBuilder.append(String.format("%02x", b));
           }

			
		} 
		catch (Exception e) {
			System.out.println("the error in mdhash is = " + e.toString());
		}
         
		
		return finaBuilder.toString();
	}

}
