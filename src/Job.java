
public class Job {
	private String jobName;
	private int startTime;
	private int endTime;
	private int value;
	private Job compatable; 
	private int compatabilityIndex;
	Job(String jobName,int startTime, int endTime, int value){
		this.jobName=jobName;
		this.startTime=startTime;
		this.endTime=endTime;
		this.value=value;
		this.compatabilityIndex = Integer.MAX_VALUE;
	}
	
	public String jobName() {
		return this.jobName;
	}
	public int getStartTime() {
		return this.startTime;
	}
	public int getEndTime() {
		return this.endTime;
	}
	public int getValue(){
		return this.value;
	}
	public void setCompatability(Job compatable) {
		this.compatable = compatable;
	}
	public void setCompatabilityIndex(int index) {
		this.compatabilityIndex= index;
	}
	public int getCompatabilityIndex() {
		
		if(this.compatabilityIndex!=Integer.MAX_VALUE) {
			return this.compatabilityIndex;	
		}
		return 0;
	}
	public Job getCompatability() {
		if(this.compatable!=null) {
			return this.compatable;	
		}
		return null;
	}

}
