package com.revature.projecttwo.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
	private static Logger logger = LogManager.getLogger();

	@Autowired
	public JavaMailSender emailSender;

	public void sendSimpleMessage(String to, String subject, String text) {
		logger.info("Sending simple message " + to + " " + subject + " " + text);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	public void sendRegister(String to, String password) {
		logger.info("Sending simple message " + to);
		MimeMessage mimeMessage = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			String htmlMsg = "<p>Salutations, compatriot.<br><br> Hail comrade! <br><br>"
					+ "You have been successfully registered with FriendScape.<br><br>"
					+ "Please login to set up your profile.<br><br>" + "Email: <strong>" + to
					+ "</strong><br><br>Password: <strong>" + password + "</strong><p>";
			mimeMessage.setContent(htmlMsg, "text/html");
			helper.setTo(to);
			helper.setFrom("TeamFiveSocial@gmail.com");
			helper.setSubject("Welcome to Friendscape");
		} catch (MessagingException e) {
			logger.error("ERROR" + e.getMessage());
		}
		emailSender.send(mimeMessage);
	}

	public void sendReset(String to, String newPassword) {
		logger.info("Sending simple message " + to);

		MimeMessage mimeMessage = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			String htmlMsg = "<p>Remembering your password can be hard, we get it. <br><br>"
					+ "We created a new one for you so you can get back into your account. <br><br>"
					+ "Your new password is: <strong>" + newPassword
					+ "</strong><br><br>Don't forget to change it!<p><br>"
					+ "<a href=\"http://localhost:8080/login\">Login</a>";
			mimeMessage.setContent(htmlMsg, "text/html");
			helper.setTo(to);
			helper.setFrom("TeamFiveSocial@gmail.com");
			helper.setSubject("Password Reset");
		} catch (MessagingException e) {
			logger.error("ERROR" + e.getMessage());
		}
		emailSender.send(mimeMessage);

	}
}