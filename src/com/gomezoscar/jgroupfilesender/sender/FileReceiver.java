package com.gomezoscar.jgroupfilesender.sender;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.UnknownHostException;

import com.gomezoscar.jgroupfilesender.utils.Constants;

public class FileReceiver extends UDPProcessor{
	
	public FileReceiver(String _ip, int _port) throws UnknownHostException {
		super(_ip, _port);
	}

	
	public void receiveFile() throws 
			IOException
	{
		this.openSocket();
		String fileName=this.receiveLine();
		System.out.println(fileName);
		String fileSize=this.receiveLine();
		System.out.println(fileSize);
		long totalBlocks=Integer.valueOf( this.receiveLine() );
		System.out.println(totalBlocks);
		
		File file=new File(fileName);
		FileOutputStream fos=new FileOutputStream(file);
		DatagramPacket packet;
		
		
		for (long block=0; block<totalBlocks; block++){
			byte[] buf = new byte[Constants.BLOCK_SIZE];
		    packet = new DatagramPacket(buf, buf.length);
		    this.senderSocket.receive(packet);
		    System.out.println((1+block) + " of " + totalBlocks);
		    fos.write(buf);
		}
	}
	

}
