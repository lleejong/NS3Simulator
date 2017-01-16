package proj.skt.Job;

public class NS3Data {
	// transmitter
	private double txLoss;
	private double txDelay;
	private double txJitter;

	// receiver
	private double rxLoss;
	private double rxDelay;
	private double rxJitter;
	
	private double throughput;

	public NS3Data(double txLoss, double txDelay, double txJitter, double rxLoss, double rxDelay, double rxJitter) {
		this.txLoss = txLoss;
		this.txDelay = txDelay;
		this.txJitter = txJitter;
		this.rxLoss = rxLoss;
		this.rxDelay = rxDelay;
		this.rxJitter = rxJitter;
		this.throughput = 0.0;
	}
	
	public NS3Data(double txLoss, double txDelay, double txJitter, double rxLoss, double rxDelay, double rxJitter, double throughput){
		this(txLoss, txDelay, txJitter, rxLoss, rxDelay, rxJitter);
		this.throughput = throughput;
	}

	public double getTxLoss() {
		return txLoss;
	}

	public void setTxLoss(double txLoss) {
		this.txLoss = txLoss;
	}

	public double getTxDelay() {
		return txDelay;
	}

	public void setTxDelay(double txDelay) {
		this.txDelay = txDelay;
	}

	public double getTxJitter() {
		return txJitter;
	}

	public void setTxJitter(double txJitter) {
		this.txJitter = txJitter;
	}

	public double getRxLoss() {
		return rxLoss;
	}

	public void setRxLoss(double rxLoss) {
		this.rxLoss = rxLoss;
	}

	public double getRxDelay() {
		return rxDelay;
	}

	public void setRxDelay(double rxDelay) {
		this.rxDelay = rxDelay;
	}

	public double getRxJitter() {
		return rxJitter;
	}

	public void setRxJitter(double rxJitter) {
		this.rxJitter = rxJitter;
	}
	
	public double getThroughput(){
		return throughput;
	}
	
	public void setThroughput(double throughput){
		this.throughput = throughput;
	}

	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append(txLoss + " " + txDelay + " " + txJitter + " " + rxLoss + " " + rxDelay  + " " + rxJitter);  
		return result.toString();
	}

	public String toStringWithTagName(){
		StringBuilder result = new StringBuilder();
		result.append("txLoss : " + txLoss + " , ");
		result.append("txDelay : " + txDelay + " , ");
		result.append("txJitter : " + txJitter + " , ");
		result.append("rxLoss : " + rxLoss + " , ");
		result.append("rxDelay : " + rxDelay + " , ");
		result.append("rxJitter : " + rxJitter);
		return result.toString();
	}
}
