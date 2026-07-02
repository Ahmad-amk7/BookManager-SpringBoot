package com.example.demo.Data;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypto {
	
	 private static final String saltHier = "fsdgjjJKLSDF4546SDkjfdsLJhfdsflreisldnjfdk9382MNdsdO,fsdf;greae57";
	
	public static String getEnkrypt(String strKey, String strSalt, String strText)
	{
		byte[] ivByte = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivByte);
		if(strSalt == null)
		{
			strSalt = saltHier;
		}
		
		try 
		{
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec keySpec = new PBEKeySpec(strKey.toCharArray(), strSalt.getBytes(), 65536, 256);
			SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			
			return Base64.getEncoder().encodeToString(cipher.doFinal(strText.getBytes(StandardCharsets.UTF_8)));
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public static String getDekrypt(String strKey, String strSalt, String strText)
	{
		byte[] ivByte = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivByte);
		if(strSalt == null)
		{
			strSalt = saltHier;
		}
		
		try 
		{
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec keySpec = new PBEKeySpec(strKey.toCharArray(), strSalt.getBytes(), 65536, 256);
			SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
			
			return new String(cipher.doFinal(Base64.getDecoder().decode(strText)));
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	

}
