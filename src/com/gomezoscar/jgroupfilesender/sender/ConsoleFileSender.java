package com.gomezoscar.jgroupfilesender.sender;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleFileSender {

	public static void main(String[] args) {
		
		Locale currentLocale;
		ResourceBundle messages;
		currentLocale=new Locale("es", "ES");
		messages=ResourceBundle.getBundle("resources/MessagesBundle", currentLocale);
		FileSender fileSender;
		try{
			fileSender=new FileSender("224.0.0.14");
			System.out.println(messages.getString("FILE_NOT_FOUND"));
		}
		catch (UnknownHostException e){
			System.out.println(messages.getString("INVALID_IP"));
		} 
		catch (IOException e) {
			System.out.println(messages.getString("UNKNOWN_ERROR"));
		}

	}

}
