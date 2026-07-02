package com.example.demo.Services;


import com.example.demo.Repositories.EmailDetailsRepository;
import com.example.demo.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.demo.Models.EmailDetails;
import com.example.demo.Models.UserAccount;

@Service
@Transactional
public class EmailDetailsService implements EmailDetailsFace {

    private EmailDetailsRepository _EmailDetailsRepository;
    private UserRepository _UserRepository;

	@Autowired
	private final JavaMailSender _JavaMailSender;
    


	


	public EmailDetailsService(EmailDetailsRepository _EmailDetailsRepository, UserRepository _UserRepository,
			JavaMailSender _JavaMailSender) {
		super();
		this._EmailDetailsRepository = _EmailDetailsRepository;
		this._UserRepository = _UserRepository;
		this._JavaMailSender = _JavaMailSender;
	}

	@Value("${spring.mail.username}")
	private String sender;
	
	
	
	@Override
	public void InsertNewEmailDetails(EmailDetails emailDetails, String id) {
		UserAccount account = new UserAccount();
		try 
		{
			if(!emailDetails.equals(null))
			{
				account = _UserRepository.findById(id).orElse(null);
				emailDetails.setUserAccount(account);
				_EmailDetailsRepository.save(emailDetails);
			}
			
		} 
		catch (Exception e) {
			System.out.println("the error in InsertNewEmailDetails email is = " + e.toString());
		}
		
	}

	@Override
	public void SendSimpleEmail(EmailDetails emailDetails) {
		
		try 
		{
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(sender);
			mailMessage.setTo(emailDetails.getdEmailResipient());
			mailMessage.setText(emailDetails.getdEmailMessageBody());
			mailMessage.setSubject(emailDetails.getdEmailSubject());
			_JavaMailSender.send(mailMessage);
			
		} 
		catch (Exception e) {
			System.out.println("the error in Send simple email is = " + e.toString());
		}
		
		
	}

	@Override
	public void SendEmailWithAttachment(EmailDetails emailDetails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmailDetails selectEamilDetailsByuserId(String id) {
		EmailDetails mailDetails = new EmailDetails();
		UserAccount account = new UserAccount();
		try 
		{
			account = _UserRepository.findById(id).orElse(null);
			mailDetails = _EmailDetailsRepository.findByUserAccoun(account).orElseThrow();
			
			
		} 
		catch (Exception e) {
			System.out.println("the error in Send attachment email is = " + e.toString());
		}
		
		
		return mailDetails;
	}

	@Override
	public List<EmailDetails> SelectEmailDetailsByPinNummber(String pinNumpder) {
		EmailDetails details = new EmailDetails();
		List<EmailDetails> detailsList = new ArrayList<>();
		
		try 
		{
			if(pinNumpder!= null || !pinNumpder.isEmpty())
			{
				detailsList = _EmailDetailsRepository.findByDEmailPinNumber(pinNumpder);
				//return detailsList;
			}
			
		} catch (Exception e) {
			System.out.println("the error in selectEmailByPinNummber is = " + e.toString());
		}
		return detailsList;
	}

	@Override
	public void DeleteEmailDetails(String id) {
		EmailDetails details = new EmailDetails();
		try 
		{
			if(!id.isEmpty() || id != null)
			{
				details =_EmailDetailsRepository.findById(id).orElse(null);
				_EmailDetailsRepository.delete(details);
			}
			
		} 
		catch (Exception e) {
			System.out.println("the error in DeleteEmailDetails is = " + e.toString());
		}
		
	}









}
