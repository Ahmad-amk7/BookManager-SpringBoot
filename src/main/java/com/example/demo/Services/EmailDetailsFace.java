package com.example.demo.Services;





import java.util.List;

import com.example.demo.Models.EmailDetails;

public interface EmailDetailsFace {
	
	void InsertNewEmailDetails(EmailDetails emailDetails, String id);
	
	void SendSimpleEmail(EmailDetails emailDetails);
	
	void SendEmailWithAttachment(EmailDetails emailDetails);
	
	void DeleteEmailDetails(String id);
	
	EmailDetails selectEamilDetailsByuserId(String id);
	
	List<EmailDetails> SelectEmailDetailsByPinNummber(String pinNumpder);

}
