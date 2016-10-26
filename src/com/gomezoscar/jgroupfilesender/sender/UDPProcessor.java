package com.gomezoscar.jgroupfilesender.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.gomezoscar.jgroupfilesender.utils.Constants;
import com.gomezoscar.jgroupfilesender.utils.UDPInputStream;
import com.gomezoscar.jgroupfilesender.utils.UDPOutputStream;

public class UDPProcessor {

	protected MulticastSocket senderSocket = null;
	protected InetAddress groupIP;

	public UDPProcessor() {
		super();
	}

	public void openSocket() throws IOException {
		senderSocket= new MulticastSocket (Constants.UDP_PORT);
		senderSocket.joinGroup(this.groupIP);
	}

	public void closeSocket() {
		if (senderSocket!=null){
			senderSocket.close();
		}
	}

	public PrintWriter getPrintWriter() throws UnknownHostException, SocketException, IOException {
		PrintWriter pw;
		UDPOutputStream udpStream;
		udpStream=new UDPOutputStream(
				this.groupIP.toString(), Constants.UDP_PORT);
		pw=new PrintWriter(udpStream);
		return pw;
	}
	public BufferedReader getBufferedReader() 
			throws UnknownHostException, SocketException
	{
		BufferedReader bfr;
		InputStreamReader isr;
		UDPInputStream udpStream;
		udpStream=new UDPInputStream(
				this.groupIP.toString(), Constants.UDP_PORT);
		isr=new InputStreamReader(udpStream);
		bfr=new BufferedReader(isr);
		return bfr;
	}

}