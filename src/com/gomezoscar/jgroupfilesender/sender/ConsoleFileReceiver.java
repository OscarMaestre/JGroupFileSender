package com.gomezoscar.jgroupfilesender.sender;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConsoleFileReceiver {

	public static void main(String[] args) {
		
		Locale currentLocale;
		ResourceBundle messages;
		currentLocale=new Locale("es", "ES");
		messages=ResourceBundle.getBundle("resources/MessagesBundle", currentLocale);
		FileReceiver fileReceiver;
		try{
			fileReceiver=new FileReceiver();
			fileReceiver.receiveFile("224.0.0.14");
			
		}
		catch (UnknownHostException e){
			System.out.println(messages.getString("INVALID_IP"));
		} 
		catch (IOException e) {
			System.out.println(messages.getString("UNKNOWN_ERROR"));
		}

	}

}
