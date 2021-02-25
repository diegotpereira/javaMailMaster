package br.com.java;


import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmailJava ej = new EmailJava();
		ej.setSmtpHostMail("smtp.gmail.com");
		ej.setSmtpPortMail("587");
		ej.setSmtpAuth("true");
		ej.setSmtpStarttls("true");
		ej.setUserMail("seuEmail@gmail.com");
		ej.setFromNameMail("seuNome");
		ej.setPassMail("suaSenha");
		ej.setCharsetMail("ISO-8859-1");
		ej.setSubjectMail("JavaMail");
		ej.setBodyMail(htmlMessage());
		ej.setTypeTextMail(EmailJava.TYPE_TEXT_HTML);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("destinatario1@bol.com.br", "email BOL");
		map.put("destinatario1@hotmail.com.br", "email MSN");
		
		ej.setToMailUsers(map);
		
		List<String> files = new ArrayList<String>();
		files.add("C:\\Users\\T.I\\Documents\\perfil.png");
		
		ej.setFileMails(files);
		
		
		try {
			new EnviodeEmail().enviarEmail(ej);
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
	public static String textMessage() {
		return "Aplicação Java de envio de e-mail usando JAVAMAIL";
	}
	public static String htmlMessage() {
		return " <!DOCTYPE html> " + 
	           " <html> " +
			   " <head>" + 
	           "<title> Email no formato HTML com JAVAMAIL!</title>" +
			   " </head> " +
	           " <body> " + 
			   "<div style='backgroud-color:orange; width:28%; heigth:100px;'>" +
			   "<ul>  " +
               "<li>Leia o novo tutorial JavaMail do Programando com Java.</li> " +
               "<li>Aprenda como enviar emails com anexos.</li>" +
               " <li>Aprenda como enviar emails em formato texto simples ou html.</li> " +
               "<li>Aprenda como enviar seu email para mais de um destinatario.</li>" +
               "</ul> " +
               "<p>Visite o GitHub " +
               "<a href='https://github.com/diegotpereira' target='new'>Veja mais projetos</a>" +
               "</p>" +
               "</div>" +
               "<div style='background-color:FFFFF; width:28%; height:50px;' align='center'>" +
               "Download do JavaMail<br/>" +
               "<a href='http://www.oracle.com/technetwork/java/javaee/index-138643.html'>" +
               "<img src='https://www.oracleimg.com/admin/images/ocom/hp/oralogo_small.gif'/>" +
               "</a> " +
               "</div>" +
               "</body> " +
               "</html>";
	}
}
