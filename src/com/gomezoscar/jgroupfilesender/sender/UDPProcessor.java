package com.gomezoscar.jgroupfilesender.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import com.gomezoscar.jgroupfilesender.utils.Constants;
import com.gomezoscar.jgroupfilesender.utils.UDPInputStream;
import com.gomezoscar.jgroupfilesender.utils.UDPOutputStream;

public class UDPProcessor {

	protected MulticastSocket senderSocket = null;
	protected InetAddress groupIP;
	private int BUFFER_SIZE=512;
	
	private String ip;
	private int port;
	public static ResourceBundle messages;
	
	public UDPProcessor (String _ip,int _port) throws UnknownHostException{
		this.groupIP=InetAddress.getByName(_ip);
		this.port=_port;
		
	}
	public static void printHelp(){
		System.out.print("\t");
		System.out.println(
				messages.getString("HELP_MESSAGE")
		);
	}
	public static void printResource(String msg){
		System.out.println(
				messages.getString("ERROR_MESSAGE") +": "+
				messages.getString(msg)
		);
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
	public void sendLine(String line) throws IOException{
		byte[] buf=new byte[BUFFER_SIZE];
		buf=line.getBytes();
		DatagramPacket packet=new DatagramPacket(
				buf, buf.length, this.groupIP, port
		);
		this.senderSocket.send(packet);
	}
	
	public String receiveLine() throws IOException{
		byte[] buf = new byte[BUFFER_SIZE];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        this.senderSocket.receive(packet);

        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
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
				this.groupIP.getHostAddress(), Constants.UDP_PORT);
		isr=new InputStreamReader(udpStream);
		bfr=new BufferedReader(isr);
		return bfr;
	}

}