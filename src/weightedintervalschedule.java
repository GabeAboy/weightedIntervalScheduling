import java.io.File;
import java.io.IOException;
import java.util.*;
class Sortbyroll implements Comparator<Job> 
{ 
    // Used for sorting in ascending order of 
    // roll number 
    public int compare(Job a, Job b) 
    { 
        return a.getEndTime() - b.getEndTime(); 
    } 
} 
public class weightedintervalschedule {
	
	public static void main(String[] args) {

		String[] extractor = null;
		ArrayList<String> jobNames = new ArrayList<String>();
		ArrayList<String> startTimes = new ArrayList<String>();
		ArrayList<String> endTimes = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		
		ArrayList<Job> jobs = new ArrayList<Job>();
		
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

		    	System.out.println(newLine);
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
		// Sort the array list by 
		Collections.sort(jobs, new Sortbyroll());

		for(Job x : jobs) {
			System.out.println(x.getValue());
		}
	}	

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
