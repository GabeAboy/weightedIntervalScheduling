import java.io.File;
import java.io.IOException;
import java.util.*;

public class weightedintervalschedule {
	public static void main(String[] args) {

		// Declare lecture array, use Comparator to run a min heap sort on Lecture start time
		String[] values = null;
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
//		    	newLine = newLine.trim();
		    	System.out.println(newLine);
		    	int endingIndex = newLine.indexOf(",");
		    	values = newLine.split(",");
//		    	String className = newLine.substring(1, endingIndex);
//		    	int startTime = Integer.parseInt(newLine.substring(4, 5));
//		    	int endTime = Integer.parseInt(newLine.substring(7, 8));
		    	
		    }      
		}
		catch(IOException e) {
			System.out.println("error, file name is incorrect reRun the program");	
		}
		for(String x : values) {
			System.out.println("Er"+x);
		}
	
	}

	// TODO this read from file doesn't take into account multiple digit numbers
	// This is a fault of the implementation, but doesn't not compromise the algorithm results 
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
