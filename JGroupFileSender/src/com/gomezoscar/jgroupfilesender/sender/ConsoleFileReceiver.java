package com.gomezoscar.jgroupfilesender.sender;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.gomezoscar.jgroupfilesender.utils.Constants;
import com.gomezoscar.jgroupfilesender.utils.ObserverWithPrint;
import com.gomezoscar.jgroupfilesender.utils.Utils;

public class ConsoleFileReceiver {
	
	
	public static void main(String[] args) {
		
		Locale currentLocale;
		currentLocale=new Locale("es", "ES");
		FileReceiver fileReceiver;
		try{
			fileReceiver=new FileReceiver(args[0], Constants.UDP_PORT);
			fileReceiver.setObserver(new ObserverWithPrint());
			fileReceiver.receiveMetadata();
			fileReceiver.receiveFile();
			
		}
		catch (ArrayIndexOutOfBoundsException e){
			Utils.printResource("MISSING_ARGS_MESSAGE");
			Utils.printResource("HELP_MESSAGE_RECEIVER");
		}
		catch (UnknownHostException e){
			Utils.printResource("UNKNOWN_HOST_ERROR");
		} 
		catch (IOException e) {
			Utils.printResource("UNKNOWN_ERROR");
		}


	}

}
