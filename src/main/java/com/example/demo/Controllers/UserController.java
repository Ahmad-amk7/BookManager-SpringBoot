package com.example.demo.Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Data.Encrypto;
import com.example.demo.Data.Generator;
import com.example.demo.Data.MDHash;
import com.example.demo.Models.EmailDetails;
import com.example.demo.Models.UserAccount;
import com.example.demo.Models.UserStamp;
import com.example.demo.Repositories.EmailDetailsRepository;
import com.example.demo.Services.BookService;
import com.example.demo.Services.EmailDetailsService;
import com.example.demo.Services.UserService;
import com.example.demo.Services.UserStampService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Null;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class UserController {


	
	private UserService _userService;
	private EmailDetailsService _emailDetailsService;
	private UserStampService _userStampService;
	
	

	public UserController(UserService _userService, EmailDetailsService _emailDetailsService,
			UserStampService _userStampService) {
		super();
		this._userService = _userService;
		this._emailDetailsService = _emailDetailsService;
		this._userStampService = _userStampService;
	}






	@GetMapping("/index")
	public String getMethodName() {
		return "index";
	}
	
	
	@GetMapping("/getUserSignIn")
	public String getSignInAccount() {
		return "get_sign_in";
	}
	
	@GetMapping("/getCreateAccount")
	public String getCreateNewAccount() {
		return "create_new_account";
	}
	
	@GetMapping("/AccountVerifikation/{id}")
	public String getVerifikationAcc(
			//@PathVariable("id") String id
			//HttpSession session
			) {
		//String userId = session.getAttribute("userId").toString();
		//session.setAttribute("userId", userId);
		return "account_verifikation";
	}
	
	
	
	
	// post methods 
	
	@PostMapping("/getCreateNewAccount")
	public String postCreateAccount(
			@ModelAttribute UserAccount account,
			HttpSession session,
			RedirectAttributes redirectAttributes
			) {
		
		EmailDetails mailDetails = new EmailDetails();
		final String pinNum = Generator.getGenerateNumber();
		LocalDateTime localDateTime = LocalDateTime.now();
		UserAccount userAccount = new UserAccount();
		UserStamp stamp = new UserStamp();
		
		userAccount = _userService.SelectUserByUserEmail(account.getUserName());
		if(userAccount != null)
		{
			redirectAttributes.addFlashAttribute("message", "Der Benutzer existiert bereits");
			return "redirect:/getCreateAccount";
		}
		final String userId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
		String stm1 = Generator.getRandomText();
		String stm2 = Generator.getRandomText();
		String stm3 = Generator.getRandomText();
		String stm4 = Generator.getRandomText();
		String stmSalt = Generator.getRandomText();
		
		// account.getUserPass(), stm1,stm2, stm3, stm4
		
		final String uPass = account.getUserPass();
		String passHash = MDHash.getHashText(uPass, stm1,stm2, stm3, stm4);
		
		String stmEncrypt1 = Encrypto.getEnkrypt(uPass, stmSalt, stm1);
		String stmEncrypt2 = Encrypto.getEnkrypt(uPass, stmSalt, stm2);
		String stmEncrypt3 = Encrypto.getEnkrypt(uPass, stmSalt, stm3);
		String stmEncrypt4 = Encrypto.getEnkrypt(uPass, stmSalt, stm4);
		String stmEncrtSalt = Encrypto.getEnkrypt(uPass, null, stmSalt);
		
		account.setUserId(userId);
		account.setUserName(account.getUserName());
		account.setUserPass(passHash);
		account.setUserConfirmPass(passHash);
		account.setIsActive(false);
	
		_userService.InsertNewUser(account);
		
		stamp.setStampFist(stmEncrypt1);
		stamp.setStampSecond(stmEncrypt2);
		stamp.setStampPlus(stmEncrypt3);
		stamp.setStampLast(stmEncrypt4);
		stamp.setStampSalt(stmEncrtSalt);
		stamp.setUserAccount(account);
		_userStampService.InsertNewStamp(stamp, account.getUserId());
		
		
		mailDetails.setdEmailId(UUID.randomUUID().toString().substring(0, 8));
		mailDetails.setdEmailResipient(account.getUserName());				
		mailDetails.setdEmailSubject("ein Test von Spring");
		mailDetails.setdEmailMessageBody(getMessagetext("Ahmad", pinNum, account.getUserId()));
		mailDetails.setdEmailLocalDateTime(localDateTime);
		mailDetails.setdEmailPinNumber(pinNum);
		mailDetails.setUserAccount(account); 
		_emailDetailsService.SendSimpleEmail(mailDetails);
		_emailDetailsService.InsertNewEmailDetails(mailDetails, account.getUserId());
		
		
		return "redirect:/AccountVerifikation/" + userId;
	}
	
	@Transactional
	@PostMapping("/SignInAccount")
	public String postSignInAccount(
			@ModelAttribute UserAccount userAccount,
			HttpSession session,
			RedirectAttributes redirectAttributes
			) {
		String pinNum = Generator.getGenerateNumber();
		UserAccount accountData = new UserAccount();
		UserStamp stamp = new UserStamp();
		EmailDetails mailDetails = new EmailDetails();
		//LocalDateTime localDateTime = LocalDateTime.now();
		accountData = _userService.SelectUserByUserEmail(userAccount.getUserName());
		if(accountData == null)
		{
			redirectAttributes.addFlashAttribute("falseMessage", "E-Mail Address oder das Passwort nich korrekt");
			return "redirect:/getUserSignIn";
		}
		
		if(!accountData.isIsActive() && accountData.getUserName().equals(userAccount.getUserName()))
		{
			redirectAttributes.addFlashAttribute("falseMessage", "Das Konto wurde nicht activiert, "
					+ "\n Bitte prüfen Sie Ihre E-Mail und activieren Sie das Konto!");
			return "redirect:/getUserSignIn";
		}
		
		if(!accountData.getUserName().equals(userAccount.getUserName()))
		{
			redirectAttributes.addFlashAttribute("falseMessage", "Das Konto wurde nicht activiert, "
					+ "\n Bitte prüfen Sie Ihre E-Mail und activieren Sie das Konto!");
			return "redirect:/getUserSignIn";
		}
		
		if(accountData != null)
		{
			
			stamp = _userStampService.SelectStampByUserId(accountData.getUserId());
			String str1 = stamp.getStampFist();
			String str2 = stamp.getStampSecond();
			String str3 = stamp.getStampPlus();
			String str4 = stamp.getStampLast();
			String strSalt = stamp.getStampSalt();
			System.out.println("the getStampFist is = " + str1 + "\n    " + str2 + "\n    " + str3);
			
			String passHier = userAccount.getUserPass();
			System.out.println("  the passHier is =  " + passHier);
			String stmDecryptSalt = Encrypto.getDekrypt(passHier, null, strSalt);
			
			String stmDecrypt1 = Encrypto.getDekrypt(passHier, stmDecryptSalt, str1);
			String stmDecrypt2 = Encrypto.getDekrypt(passHier, stmDecryptSalt, str2);
			String stmDecrypt3 = Encrypto.getDekrypt(passHier, stmDecryptSalt, str3);
			String stmEDerypt4 = Encrypto.getDekrypt(passHier, stmDecryptSalt, str4);
			
			System.out.println("  the stmDecrypt1 is =  " + stmDecrypt1 + "\n stmDecryptSalt is = " + stmDecryptSalt);
			
			String passHash = MDHash.getHashText(passHier, stmDecrypt1, stmDecrypt2, stmDecrypt3, stmEDerypt4);
			System.out.println("the passHash is = " + passHash);
			
			if(accountData.getUserName().equals(userAccount.getUserName()) 
					&& accountData.getUserPass().equals(passHash)
					) {
				
				
				session.setAttribute("userId", accountData.getUserId());
				return "redirect:/getMyBooks";
			}
			else {
				redirectAttributes.addFlashAttribute("message", "entweder dass Passwort oder die Benutznae ist nicht rechtig ");
				return "redirect:/getUserSignIn";
			}
		}
				
		return null;
	}
	

	@PostMapping("/getVerifikationn/{id}")
	public String postMethodName(
			@PathVariable("id") String id,
			@RequestParam("PinNumber") String pinNumber,
			HttpSession session,
			RedirectAttributes redirectAttributes
			) {
		
		//String userId = session.getAttribute("userId").toString();
		UserAccount account = new UserAccount();
		UserAccount account1 = new UserAccount();
		EmailDetails details = new EmailDetails();
		List<EmailDetails> emailDetails = new ArrayList<>();
		emailDetails = _emailDetailsService.SelectEmailDetailsByPinNummber(pinNumber);
		
		if(emailDetails != null)
		{
			System.out.println("email details List not null!!!!");
			for(EmailDetails itemDetails : emailDetails)
			{
				System.out.println("email details List !!!!" + itemDetails.getdEmailId() + " " + itemDetails.getdEmailResipient());
				details.setdEmailId(itemDetails.getdEmailId());
				details.setdEmailLocalDateTime(itemDetails.getdEmailLocalDateTime());
				details.setdEmailPinNumber(itemDetails.getdEmailPinNumber());
				details.setdEmailResipient(itemDetails.getdEmailResipient());
				details.setUserAccount(itemDetails.getUserAccount());
				System.out.println("email details List !!!!" + details.getdEmailId() + " " + details.getdEmailResipient());
				
			}
		}
		
		System.out.println("#### Emaill details 1111 =  " + details.getdEmailId() + "\n" + details.getdEmailPinNumber() + "\n" + details.getdEmailResipient());
		String userEmail = details.getdEmailResipient();
		if(details != null)
		{
			account = _userService.SelectUserByUserEmail(userEmail);
		}
		String detailsId = details.getdEmailId();
		if(details.getdEmailResipient().equals(account.getUserName()))
		{
			account1.setUserId(account.getUserId());
			account1.setUserName(account.getUserName());
			account1.setUserPass(account.getUserPass());
			account1.setUserConfirmPass(account.getUserConfirmPass());
			account1.setIsActive(true);
			_userService.UpdaateAccount(account1, account.getUserId());
			_emailDetailsService.DeleteEmailDetails(detailsId);
			return "redirect:/getUserSignIn";
		}
				
					
		return "redirect:/AccountVerifikation/" + id;
	}
	
	
	//Message
	private static String getMessagetext(String name, String pinNum, String id)
	{
		String messageText = " Hallo '"+name+"' \n \n  Das ist ein Test \n \n "
				+ "Bitte geben Sie diese Nummer '"+pinNum+"' ein, um Ihr Konto zu activieren"
				+"\n Klicken Sie bitte auf den Link, um zur Aktivierungsseite zu gehen."
				+"\n der Link:  http://localhost:8082/AccountVerifikation/{id}" + id;
		
		  
		return messageText;
	}
	
	
	
	// http://localhost:8082/AccountVerifikation/" + id
	
	

}
