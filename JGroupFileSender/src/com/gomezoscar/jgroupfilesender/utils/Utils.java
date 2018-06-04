package com.gomezoscar.jgroupfilesender.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Utils {
	public static ResourceBundle loadMessages(){
		Locale currentLocale = Locale.getDefault();
		currentLocale=new Locale("es", "ES");
		//ResourceBundle messages=ResourceBundle.getBundle("MessagesBundle", currentLocale);
		ResourceBundle messages=ResourceBundle.getBundle("resources.MessagesBundle", currentLocale);
		return messages;
	}
	
	public static void printHelp(){
		ResourceBundle messages=loadMessages();
		System.out.print("\t");
		System.out.println(
				messages.getString("HELP_MESSAGE")
		);
	}
	public static void printResource(String msg){
		ResourceBundle messages=loadMessages();
		System.out.println(
				messages.getString("ERROR_MESSAGE") +": "+
				messages.getString(msg)
		);
	}
	

}
