package com.gomezoscar.jgroupfilesender.sender;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.gomezoscar.jgroupfilesender.utils.Constants;

public class ConsoleFileReceiver {
	
	public static ResourceBundle messages;
	public static void printHelp(){
		System.out.print("\t");
		System.out.println(
				messages.getString("HELP_MESSAGE")
		);
	}
	public static void printResource(String msg){
		System.out.println(
				messages.getString("ERROR_MESSAGE") +": "+
				messages.getString(msg)
		);
	}
	public static void main(String[] args) {
		
		Locale currentLocale;
		currentLocale=new Locale("es", "ES");
		messages=ResourceBundle.getBundle("resources/MessagesBundle", currentLocale);
		FileReceiver fileReceiver;
		try{
			fileReceiver=new FileReceiver("224.0.0.1", Constants.UDP_PORT);
			fileReceiver.receiveFile();
			
		}
		catch (ArrayIndexOutOfBoundsException e){
			printResource("MISSING_ARGS_MESSAGE");
			printHelp();
			return ;
		}
		catch (UnknownHostException e){
			e.printStackTrace();
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println(messages.getString("UNKNOWN_ERROR"));
		}


	}

}
