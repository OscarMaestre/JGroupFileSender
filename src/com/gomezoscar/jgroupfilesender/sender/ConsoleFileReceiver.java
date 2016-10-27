package com.gomezoscar.jgroupfilesender.sender;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.gomezoscar.jgroupfilesender.utils.Constants;

public class ConsoleFileReceiver {

	public static void main(String[] args) {
		
		Locale currentLocale;
		ResourceBundle messages;
		currentLocale=new Locale("es", "ES");
		messages=ResourceBundle.getBundle("resources/MessagesBundle", currentLocale);
		FileReceiver fileReceiver;
		try{
			fileReceiver=new FileReceiver("224.0.0.1", Constants.UDP_PORT);
			fileReceiver.receiveFile(args[0]);
			
		}
		catch (UnknownHostException e){
			e.printStackTrace();
			System.out.println(messages.getString("INVALID_IP"));
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println(messages.getString("UNKNOWN_ERROR"));
		}

	}

}
