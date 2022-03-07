package org.project.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.project.blindings.User;
import org.project.entities.UserEntity;
import org.project.repositories.UserRepository;
import org.project.util.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ForgotPwdServiceImpl implements ForgotPwdService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private  EmailUtils emailUtils;
	
	@Override
	public String forgotpwd(String email) {
		UserEntity user=userRepo.findByUserEmail(email);
		if(user==null)
		{
			return "No Record Founded with  given Email";
		}
		return null;
	}
	public boolean sendForgotPwd(UserEntity user) {
		String to=user.getUserEmail();
		String fname=user.getUserFirstName();
		
		String pwd=user.getUserPwd();
		String subject="forgot Passsword -AshokIt";
		String body="";
			  
		return emailUtils.sendEmail(subject, body, to);
		
	}
	
public String readMailBody(String fileName,User user) {
	String mailBody=null;
	
	StringBuffer buffer=new StringBuffer();
	
	Path path=Paths.get(fileName);
	try(Stream<String> stream=Files.lines(path)){
		stream.forEach(line ->{buffer.append(line);
	});
		
		 mailBody =buffer.toString();
		 mailBody= mailBody.replace("{FNAME}",user.getUserFirstName());
		 mailBody= mailBody.replace("{EMAIL}",user.getUserEmail());
		 mailBody= mailBody.replace("{{TEMP-PWD}}",user.getUserPwd());
}
	catch(IOException e) {
		e.printStackTrace();
	}
	
	
	return mailBody;
	
	
}
	

}
