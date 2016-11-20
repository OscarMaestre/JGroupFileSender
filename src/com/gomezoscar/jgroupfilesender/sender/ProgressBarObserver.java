package com.gomezoscar.jgroupfilesender.sender;

import com.gomezoscar.jgroupfilesender.utils.Observer;

import javafx.scene.control.ProgressBar;

public class ProgressBarObserver implements Observer {
	long totalBlocks=0;
	long sentBlocks=0;
	long receivedBlocks=0;
	int step;
	ProgressBar progressBar;
	public ProgressBarObserver(ProgressBar p, int _step){
		this.step=_step;
		this.progressBar=p;
	}
	@Override
	public void blockSent() {
		sentBlocks++;
		if ( (sentBlocks % step) == 0 ){
			this.calculateProgress(sentBlocks, totalBlocks);
		}

	}
	@Override
	public void blockReceived() {
		receivedBlocks++;
		
		if ( (receivedBlocks % step) == 0 ){
			
			this.calculateProgress(receivedBlocks, totalBlocks);
		}

	}

	@Override
	public void setTotalBlocks(long blocks) {
		this.totalBlocks=blocks;

	}
	public double calculateProgress(long amount, long total){
		double progress= (double) amount / total ;
		System.out.print("\r"+amount+" "+total + " "+ progress);
		this.progressBar.setProgress(progress);
		return progress;
	}


}
