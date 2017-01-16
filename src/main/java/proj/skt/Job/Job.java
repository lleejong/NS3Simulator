package proj.skt.Job;

public class Job{
	
	private NS3Data input;
	
	public Job(NS3Data input){
		this.input = input;
	}
	
	public NS3Data getNS3Data(){
		return input;
	}
}
