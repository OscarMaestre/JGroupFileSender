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
	
	public long totalBlocks;


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
		totalBlocks =1+(long) Math.ceil (fileSize / Constants.BLOCK_SIZE);
		System.out.println("Block to send:"+totalBlocks);
		if (this.observer!=null){
			this.observer.setTotalBlocks(totalBlocks);
		}
		this.sendLine(String.valueOf(totalBlocks));
	}
	public void sendFile (String filename, Observer observer) 
			throws IOException, InterruptedException
	{
		this.openSocket();
		this.sendMetadata(filename);
		FileInputStream fis;
		File file=new File(filename);
		fis=new FileInputStream(filename);
		byte[] block=new byte[Constants.BLOCK_SIZE];
		int read=fis.read(block);
		long count=0;
		DatagramPacket packet=new DatagramPacket(block, read, this.groupIP, Constants.UDP_PORT);
		packet.setData(block);
		packet.setLength(read);
		count++;
		System.out.println(count+" " +packet.getLength());
		while (read!=-1){
			senderSocket.send(packet);
			//observer.blockSent();
			read=fis.read(block);
			if (read!=-1){
				packet.setData(block);
				packet.setLength(read);
				System.out.println(count+" " +packet.getLength());
				
			}
			Thread.sleep(50);
			
		}
		fis.close();
	}	
}
