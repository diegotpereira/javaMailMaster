package br.com.java;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Map;

public class EnviodeEmail {
	
	public void enviarEmail(final EmailJava mail) throws  UnsupportedEncodingException, MessagingException{
		
		Properties pr = new Properties();
		pr.setProperty("mail.transport.protocol", "smtp");
		pr.setProperty("mail.host", mail.getSmtpHostMail());
		pr.setProperty("mail.smtp.auth", mail.getSmtpAuth());
		pr.setProperty("mail.smtp.starttls.enable", mail.getSmtpStarttls());
		pr.setProperty("mail.smtp.port", mail.getSmtpPortMail());
		pr.setProperty("mail.mime.charset", mail.getCharsetMail());
		
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						mail.getUserMail(), mail.getPassMail()
						);
			}
		};
		
		Session session = Session.getDefaultInstance(pr, auth);
		session.setDebug(false);
		
		Message msg = new MimeMessage(session);
		msg.setFrom( new InternetAddress(mail.getUserMail(), mail.getFromNameMail()));
		
		boolean first = true;
		for (Map.Entry<String, String> map : mail.getToMailUsers().entrySet()) {
			if (first) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(map.getKey(), map.getValue()));
				first = false;
			}else {
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(map.getKey(), map.getValue()));
			}
		}
		msg.setSubject(mail.getSubjectMail());
		
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(mail.getBodyMail(), mail.getTypeTextMail());
		
		Multipart mps = new MimeMultipart();
		for (int index = 0; index < mail.getFileMails().size(); index++) {
			MimeBodyPart attachFilePart = new MimeBodyPart();
			
			FileDataSource fds = new FileDataSource(mail.getFileMails().get(index));
			
			attachFilePart.setDataHandler(new DataHandler(fds));
			attachFilePart.setFileName(fds.getName());
			
			mps.addBodyPart(attachFilePart, index);
		}
		mps.addBodyPart(textPart);
		msg.setContent(mps);
		Transport.send(msg);
	}
}
