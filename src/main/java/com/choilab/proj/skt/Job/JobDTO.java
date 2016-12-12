package com.choilab.proj.skt.Job;

public class JobDTO {
	private double TxLoss;
	private int TxDelay;
	private int TxJitter;
	
	private double RxLoss;
	private int RxDelay;
	private int RxJitter;
	
	public double getTxLoss() {
		return TxLoss;
	}
	public void setTxLoss(double txLoss) {
		TxLoss = txLoss;
	}
	public int getTxDelay() {
		return TxDelay;
	}
	public void setTxDelay(int txDelay) {
		TxDelay = txDelay;
	}
	public int getTxJitter() {
		return TxJitter;
	}
	public void setTxJitter(int txJitter) {
		TxJitter = txJitter;
	}
	public double getRxLoss() {
		return RxLoss;
	}
	public void setRxLoss(double rxLoss) {
		RxLoss = rxLoss;
	}
	public int getRxDelay() {
		return RxDelay;
	}
	public void setRxDelay(int rxDelay) {
		RxDelay = rxDelay;
	}
	public int getRxJitter() {
		return RxJitter;
	}
	public void setRxJitter(int rxJitter) {
		RxJitter = rxJitter;
	}
}
