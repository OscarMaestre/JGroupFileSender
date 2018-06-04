package com.gomezoscar.jgroupfilesender.sender;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.UnknownHostException;

import com.gomezoscar.jgroupfilesender.utils.Constants;

public class FileReceiver extends UDPProcessor implements Runnable{
	String filename;
	long fileSize, totalBlocks;

	public FileReceiver(String _ip, int _port) throws UnknownHostException {
		super(_ip, _port);
	}
	
	public void receiveMetadata() throws IOException{
		this.openSocket();
		String fileName=this.receiveLine();
		this.filename=fileName;
		System.out.println(fileName);
		String fileSize=this.receiveLine();
		this.fileSize=Long.parseLong(fileSize);
		System.out.println(fileSize);
		totalBlocks=Long.valueOf( this.receiveLine() );
		System.out.println(totalBlocks);
	}
	
	
	public String getFilename() {
		return filename;
	}

	public long getFileSize() {
		return fileSize;
	}

	public long getTotalBlocks() {
		return totalBlocks;
	}

	public void receiveFile() throws 
			IOException
	{
		
		System.out.println("Receiving");
		File file=new File(filename);
		FileOutputStream fos=new FileOutputStream(file);
		DatagramPacket packet;
		
		if (this.observer!=null) {
			this.observer.setTotalBlocks(totalBlocks);
		}
		
		byte[] buf = new byte[Constants.BLOCK_SIZE];
		packet = new DatagramPacket(buf, buf.length);
		for (long block=1; block<=totalBlocks; block++){
		    this.senderSocket.receive(packet);
		    if (this.observer!=null) {
		    	//observer.blockReceived();
		    	System.out.println(block+" "+packet.getLength());
		    }
		    System.out.print("#");
		    fos.write(buf, 0, packet.getLength());
		    //fos.write(buf);
		}
		fos.flush();
		fos.close();
	}

	@Override
	public void run() {
		try {
			this.receiveFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
