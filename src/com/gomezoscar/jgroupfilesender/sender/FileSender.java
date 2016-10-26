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
	
	public void sendMetadata(String filename) 
			throws UnknownHostException, SocketException, IOException
	{
		File file=new File(filename);
		long fileSize=file.length();
		
		PrintWriter pw=getPrintWriter();
		pw.println(filename);
		pw.flush();
		pw.println(fileSize);
		pw.flush();
	}
	public void sendFile (String ip, String filename, Observer observer) 
			throws IOException{
		this.openSocket(ip);
		this.sendMetadata(filename);
		FileInputStream fis;
		fis=new FileInputStream(filename);
		byte[] block=new byte[Constants.BLOCK_SIZE];
		int read=fis.read(block);
		DatagramPacket packet=new DatagramPacket(block, read);
		while (read!=-1){
			senderSocket.send(packet);
			observer.blockSent();
			read=fis.read(block);
			packet.setData(block);
		}
		fis.close();
	}
	
	
	public void sendFile (String ip, String filename) 
			throws IOException{
		this.openSocket(ip);
		this.sendMetadata(filename);
		FileInputStream fis;
		File file=new File(filename);
		fis=new FileInputStream(filename);
		byte[] block=new byte[Constants.BLOCK_SIZE];
		int read=fis.read(block);
		DatagramPacket packet=new DatagramPacket(block, read);
		while (read!=-1){
			senderSocket.send(packet);
			//observer.blockSent();
			read=fis.read(block);
			packet.setData(block);
		}
		fis.close();
	}
}
