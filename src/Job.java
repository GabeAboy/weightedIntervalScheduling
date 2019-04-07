
public class Job {
	String jobName;
	int startTime;
	int endTime;
	int value;
	
	Job(String jobName,int startTime, int endTime, int value){
		this.jobName=jobName;
		this.startTime=startTime;
		this.endTime=endTime;
		this.value=value;
	}
	
	String jobName() {
		return this.jobName;
	}
	int getStartTime() {
		return this.startTime;
	}
	int getEndTime() {
		return this.endTime;
	}
	int getValue(){
		return this.value;
	}

}
