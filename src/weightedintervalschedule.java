import java.io.File;
import java.io.IOException;
import java.util.*;
class SortByEndTime implements Comparator<Job> 
{ 
    // Used for sorting in ascending order of 
    // roll number 
    public int compare(Job a, Job b) 
    { 
        return a.getEndTime() - b.getEndTime(); 
    } 
} 
public class weightedintervalschedule {
	private static int M[] = null;
	private static ArrayList<Job> jobs = null;
	
	public static void main(String[] args) {
		
		String[] extractor = null;
		ArrayList<String> jobNames = new ArrayList<String>();
		ArrayList<String> startTimes = new ArrayList<String>();
		ArrayList<String> endTimes = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		
		jobs = new ArrayList<Job>();
		
//        String fileName = ReadUserInput();
		
		try {
			File file = new File("jobs.txt"); 
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(file); 
			
		    while (sc.hasNextLine()) {	
		    	String newLine = sc.nextLine();	
		    	newLine = newLine.replace("(", "");
		    	newLine = newLine.replace(")", "");
		    	newLine = newLine.replace(" ", "");
		    	extractor = newLine.split(",");
		    	
		    	jobNames.add(extractor[0]);
		    	startTimes.add(extractor[1]);
		    	endTimes.add(extractor[2]);
		    	values.add(extractor[3]);
		    	
		    	jobs.add(	     new Job(extractor[0],
		    			Integer.parseInt(extractor[1]),
		    			Integer.parseInt(extractor[2]),
		    			Integer.parseInt(extractor[3])));
		    	
		    }      
		}
		catch(IOException e) {
			System.out.println("error, file name is incorrect reRun the program");	
		}
		
		
		M = new int [jobs.size()];
		for(int i = 1; i<jobs.size();i++) {
			M[i]=Integer.MAX_VALUE;
		}
		M[0] = 0;
//		WeightedIntervalScheduling(jobs.size()-1);

		Collections.sort(jobs, new SortByEndTime());
		for( Job x : jobs)
		{
			System.out.println(x.jobName()+"  "+x.getStartTime()+" "+x.getEndTime()+" "+x.getValue());
			// Compute p(j) for each job in jobs
		}
		
		//TODO Compute p(j)
		int x = 0;
		for( int j = jobs.size()-1; j>=0; j--) {
			//Need to find compatible with this 
			int jobJScannedEndTime = jobs.get(j).getStartTime();
			for( int i = j -x; i>=0; i--) {
		
				//Need to find compatible with this 
				int checkIfCompat = jobs.get(i).getEndTime();
			
				if(checkIfCompat < jobJScannedEndTime) {
				
					jobs.get(j).setCompatability(jobs.get(i));
					jobs.get(j).setCompatabilityIndex(i);
				}
			}
			x++;
		}
		for( Job xx : jobs)
		{
			Job nn = xx.getCompatability();
			if(nn!=null) {
				
				System.out.println(xx.getEndTime()+"Compat"+Integer.toString(nn.getEndTime()));
			}
			// Compute p(j) for each job in jobs
		}
		System.out.println("sss"+jobs.get(2).getCompatabilityIndex());
		for( int pp : M)
		{
			System.out.println(pp);
			// Compute p(j) for each job in jobs
		}
		System.out.println("Max weight "+WeightedIntervalScheduling(jobs.size()-1));
		
		for( int pp : M)
		{
			System.out.println(pp);
			// Compute p(j) for each job in jobs
		}
		
	}
	
	//Input: n, s1,sn, f1,fn v1...vn
	//global array:M[0,n] M[j] = result of compute-opt(j)
	public static int WeightedIntervalScheduling(int j) {
		
		if(j!=-1) {
			
			if(M[j]==Integer.MAX_VALUE) {
				
				M[j] = Math.max(jobs.get(j).getValue() + WeightedIntervalScheduling(  jobs.get(j).getCompatabilityIndex()    ), WeightedIntervalScheduling(j-1));
			}
		}
		return M[j];		
		
	}
	
	
	//function to find 
	
	
	//compute p(1),p(2)
	
	//for j=1 to n
		//M[j] = empty
	//M[0]=0
	//run M-compute-opt(n)
	//M-compute-opt(j){
		//if(M[j] is empty)// not computed yet
			//M[j] = max(vj + M-compute-opt(p(j)), M-compute-opt(p(j-1))
		// return M[j]
	//}
    public static String ReadUserInput() {
    	Scanner reader = new Scanner(System.in);
    	String userInput="";
    	System.out.println("Enter your file name: ");
    	try {
    		userInput = reader.nextLine();
    		
    	}catch(InputMismatchException a) {		
    		System.out.println("Please enter an existing file name");
    		ReadUserInput();
    	}
    	reader.close();
    	return userInput;
    }
  
    
}
