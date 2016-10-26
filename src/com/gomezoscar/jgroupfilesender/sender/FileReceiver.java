package com.gomezoscar.jgroupfilesender.sender;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import com.gomezoscar.jgroupfilesender.utils.Constants;

public class FileReceiver extends UDPProcessor{
	private MulticastSocket senderSocket=null;
	private InetAddress groupIP;
	public FileReceiver ( String groupIP ) throws UnknownHostException {
		this.groupIP=InetAddress.getByName(groupIP);
		
	}
	

}
