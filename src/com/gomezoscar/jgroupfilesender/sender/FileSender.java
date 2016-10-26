package com.gomezoscar.jgroupfilesender.sender;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import com.gomezoscar.jgroupfilesender.utils.Constants;
import com.gomezoscar.jgroupfilesender.utils.Observer;
public class FileSender {
	private MulticastSocket senderSocket=null;
	private InetAddress groupIP;
	public FileSender ( String groupIP ) throws UnknownHostException, IOException{
		this.groupIP=InetAddress.getByName(groupIP);
		senderSocket= new MulticastSocket (Constants.UDP_PORT);
	}
	public void sendFile (String filename, Observer observer) 
			throws IOException{
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
	public void sendFile (String filename) 
			throws IOException{
		FileInputStream fis;
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
