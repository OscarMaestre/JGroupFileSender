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
			fileReceiver.receiveFile();
			
		}
		catch (ArrayIndexOutOfBoundsException e){
			Utils.printResource("MISSING_ARGS_MESSAGE");
			Utils.printHelp();
			return ;
		}
		catch (UnknownHostException e){
			e.printStackTrace();
			
		} 
		catch (IOException e) {
			e.printStackTrace();
			Utils.printResource("UNKNOWN_ERROR");
		}


	}

}
