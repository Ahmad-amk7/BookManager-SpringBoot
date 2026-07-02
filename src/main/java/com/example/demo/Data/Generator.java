package com.example.demo.Data;

import java.security.SecureRandom;
import java.util.Random;



public class Generator {
	
	
	private static final String alfaBit = "qwertzuiopü+#äölkjhgfdsayxcvbnm";
	private static final String alfaToUp =  alfaBit.toUpperCase();
	private static final String num = "0123456789";
	private static final String ch = "!§$%&&/()=?`#";
	private static final String finCh = alfaBit + alfaToUp + num + ch; 
	
	
	public static String getRandomText()
	{
		StringBuilder builder = new StringBuilder();
		SecureRandom random = new SecureRandom();
		int len = getNumber();
		try 
		{
			for(int i = 0; i < len; i++)
			{
				builder.append(finCh.charAt(random.nextInt(finCh.length())));
			}
			
		} 
		catch (Exception e) {
			System.out.println("the error in Random text is = " + e.toString());
		}
		
		return builder.toString();
	}
	
	
	public static int getNumber()
	{
		int len = 0;
		//int len1 = 0;
		Random random = new Random();
		len = random.nextInt(1000);
		//len1 = random.nextInt(999);
		
		
		return len;
	}
	
	
	public static String getGenerateNumber()
	{
		int len = 0;
		int len1 = 0;
		Random random = new Random();
		len = random.nextInt(999);
		len1 = random.nextInt(999);
		StringBuilder builder = new StringBuilder();
		builder.append(Integer.toString(len))
		.append(Integer.toString(len1))
		.toString();
		
		return builder.toString();
	}
	
	
	

	
	

}
