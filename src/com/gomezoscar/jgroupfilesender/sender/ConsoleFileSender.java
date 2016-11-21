package com.gomezoscar.jgroupfilesender.sender;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.gomezoscar.jgroupfilesender.utils.Constants;
import com.gomezoscar.jgroupfilesender.utils.Observer;
import com.gomezoscar.jgroupfilesender.utils.ObserverWithPrint;


public class ConsoleFileSender {

	public static void main(String[] args) throws InterruptedException {
		
		Locale currentLocale;
		ResourceBundle messages;
		currentLocale=new Locale("es", "ES");
		messages=ResourceBundle.getBundle("resources/MessagesBundle", currentLocale);
		FileSender fileSender;
		try{
			fileSender=new FileSender(args[0], Constants.UDP_PORT);
			//System.out.println(messages.getString("FILE_NOT_FOUND"));
			ObserverWithPrint observer=new ObserverWithPrint();
			fileSender.setObserver(observer);
			//fileSender.sendFile(args[1]);
			fileSender.sendFile(args[1], observer);
		}
		catch (UnknownHostException e){
			System.out.println(messages.getString("INVALID_IP"));
		} 
		catch (IOException e) {
			System.out.println(messages.getString("UNKNOWN_ERROR"));
		}

	}

}
