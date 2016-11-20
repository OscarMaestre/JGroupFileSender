package com.gomezoscar.jgroupfilesender.utils;

public interface Observer {
	public void blockSent();
	public void blockReceived();
	public void setTotalBlocks(long blocks);
}
