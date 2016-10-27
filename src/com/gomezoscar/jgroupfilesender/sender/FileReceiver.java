package com.gomezoscar.jgroupfilesender.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class FileReceiver extends UDPProcessor{
	
	public FileReceiver(String _ip, int _port) throws UnknownHostException {
		super(_ip, _port);
	}

	
	public void receiveFile(String ip) throws 
			IOException
	{
		this.openSocket();
		String fileName=this.receiveLine();
		System.out.println(fileName);
		String fileSize=this.receiveLine();
		System.out.println(fileSize);
	}
	

}
