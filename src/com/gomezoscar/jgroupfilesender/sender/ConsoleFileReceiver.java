package com.gomezoscar.jgroupfilesender.sender;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.gomezoscar.jgroupfilesender.utils.Constants;

public class ConsoleFileReceiver {
	
	
	public static void main(String[] args) {
		
		Locale currentLocale;
		currentLocale=new Locale("es", "ES");
		FileReceiver fileReceiver;
		try{
			fileReceiver=new FileReceiver(args[0], Constants.UDP_PORT);
			fileReceiver.receiveFile();
			
		}
		catch (ArrayIndexOutOfBoundsException e){
			UDPProcessor.printResource("MISSING_ARGS_MESSAGE");
			UDPProcessor.printHelp();
			return ;
		}
		catch (UnknownHostException e){
			e.printStackTrace();
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			UDPProcessor.printResource("UNKNOWN_ERROR");
		}


	}

}
