package com.gomezoscar.jgroupfilesender.sender;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.gomezoscar.jgroupfilesender.utils.Constants;
import com.gomezoscar.jgroupfilesender.utils.Observer;
public class FileSender extends UDPProcessor {
	
	public FileSender(String _ip, int _port) throws UnknownHostException {
		super(_ip, _port);
	}

	public void sendMetadata(String filename) 
			throws UnknownHostException, SocketException, IOException
	{
		File file=new File(filename);
		String name=file.getName();
		this.sendLine(name);
		long fileSize=file.length();
		this.sendLine(String.valueOf(fileSize));
		long totalBlocks=1 + (fileSize / Constants.BLOCK_SIZE) ;
		if (this.observer!=null){
			this.observer.setTotalBlocks(totalBlocks);
		}
		this.sendLine(String.valueOf(totalBlocks));
	}
	public void sendFile (String filename, Observer observer) 
			throws IOException
	{
		this.openSocket();
		this.sendMetadata(filename);
		FileInputStream fis;
		File file=new File(filename);
		fis=new FileInputStream(filename);
		byte[] block=new byte[Constants.BLOCK_SIZE];
		int read=fis.read(block);
		DatagramPacket packet=new DatagramPacket(block, read, this.groupIP, Constants.UDP_PORT);
		while (read!=-1){
			senderSocket.send(packet);
			observer.blockSent();
			read=fis.read(block);
			packet.setData(block);
		}
		fis.close();
	}
	
	
	public void sendFile (String filename) 
			throws IOException
	{
		this.openSocket();
		this.sendMetadata(filename);
		FileInputStream fis;
		File file=new File(filename);
		fis=new FileInputStream(filename);
		byte[] block=new byte[Constants.BLOCK_SIZE];
		int read=fis.read(block);
		DatagramPacket packet=new DatagramPacket(block, read, this.groupIP, Constants.UDP_PORT);
		while (read!=-1){
			senderSocket.send(packet);
			//observer.blockSent();
			read=fis.read(block);
			packet.setData(block);
		}
		fis.close();
	}
}
