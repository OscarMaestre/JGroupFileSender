package com.gomezoscar.jgroupfilesender.utils;

public class ObserverWithPrint implements Observer {

	long packetsSent=0;
	long packetsReceived=0;
	long totalPackets=0;
	@Override
	public void blockSent() {
		this.packetsSent++;
		System.out.print("\r"+packetsSent + "/ " + totalPackets);

	}

	@Override
	public void blockReceived() {
		this.packetsReceived++;
		System.out.print("\r"+packetsReceived + "/" + totalPackets);
	}
	@Override
	public void setTotalBlocks(long totalBlocks) {
		this.totalPackets=totalBlocks;
		
	}

}
