package com.gomezoscar.jgroupfilesender.sender;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import com.gomezoscar.jgroupfilesender.utils.*;
public class FileSender {
	private MulticastSocket senderSocket=null;
	private InetAddress groupIP;
	public FileSender ( String groupIP ) throws UnknownHostException, IOException{
		this.groupIP=InetAddress.getByName(groupIP);
		senderSocket= new MulticastSocket (Constants.UDP_PORT);
	}
	public void sendFile (String filename){
		
	}
}
