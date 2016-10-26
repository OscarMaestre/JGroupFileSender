package com.gomezoscar.jgroupfilesender.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class FileReceiver extends UDPProcessor{
	private MulticastSocket senderSocket=null;
	private InetAddress groupIP;
	
	public void receiveFile(String ip) throws 
			IOException
	{
		this.openSocket(ip);
		BufferedReader bfr=this.getBufferedReader();
		String fileName=bfr.readLine();
		System.out.println("Filename:"+fileName);
	}
	

}
