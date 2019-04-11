import java.io.File;
import java.io.IOException;
import java.util.*;

class SortByEndTime implements Comparator<Job> {
	// Used for sorting in ascending order of
	// roll number
	public int compare(Job a, Job b) {
		return a.getEndTime() - b.getEndTime();
	}
}

public class weightedintervalschedule {
	private static int M[] = null;
	private static String Op[] = null;
	private static ArrayList<Job> jobs = null;

	public static void main(String[] args) {

		String[] extractor = null;
		ArrayList<String> jobNames = new ArrayList<String>();
		ArrayList<String> startTimes = new ArrayList<String>();
		ArrayList<String> endTimes = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();

		jobs = new ArrayList<Job>();

        String fileName = ReadUserInput();

		try {
			File file = new File(fileName);
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

				jobs.add(new Job(extractor[0], Integer.parseInt(extractor[1]), Integer.parseInt(extractor[2]),
						Integer.parseInt(extractor[3])));

			}
		} catch (IOException e) {
			System.out.println("error, file name is incorrect reRun the program");
		}

		M = new int[jobs.size()+1];
		Op = new String[jobs.size()+1];
		for (int i = 0; i < Op.length; i++) {
			Op[i] = "";
		}
		for (int i = 0; i < M.length; i++) {
			M[i] = Integer.MAX_VALUE;
		}
		M[0] = 0;
		System.out.println("Sorted Job Inputs:");

		Collections.sort(jobs, new SortByEndTime());
		for (Job x : jobs) {
			System.out.println("{"+x.jobName() + "," + x.getStartTime() + "," + x.getEndTime() + "," + x.getValue()+"}");
			// Compute p(j) for each job in jobs
		}
		System.out.println();
		System.out.println("Compute P values");
		// TODO Compute p(j)
		int x = 1;
		for (int j = jobs.size() - 1; j >= 0; j--) {
			// Need to find compatible with this
			int jobJScannedEndTime = jobs.get(j).getStartTime();
			for (int i = j - x; i >= 0; i--) {

				// Need to find compatible with this
				int checkIfCompat = jobs.get(i).getEndTime();

				if (checkIfCompat < jobJScannedEndTime) {

					jobs.get(j).setCompatability(jobs.get(i));
					jobs.get(j).setCompatabilityIndex(i+1);
					
				}
				
			}
			
			
			x++;
		}
		
		for(int i = 0; i<jobs.size();i++) {
			if(jobs.get(i).getCompatability()!=null) {
				System.out.println("p("+i+") = " + jobs.get(i).getCompatabilityIndex());
			}else {				
				System.out.println("p("+i+") = 0" );
			}
		}

		System.out.println("\nMax weight " + WeightedIntervalScheduling(jobs.size()));

		for (int i = 0; i<M.length; i++) {
			System.out.println("M["+i+"] = "+M[i]);
		}
		
		//Reset M
		for (int i = 0; i < M.length; i++) {
			M[i] = Integer.MAX_VALUE;
		}
		M[0] = 0;
		
		System.out.println("Iterative:" );
		WeightedIntervalSchedulingIterative();

		for (int i = 0; i<M.length; i++) {
			System.out.println("M["+i+"] = "+M[i]);
		}
		
//		for (int i = 0; i<Op.length; i++) {
//			System.out.println("Op["+i+"] = "+Op[i]);
//		}

	}



	public static int WeightedIntervalScheduling(int j) {

		if (M[j] == Integer.MAX_VALUE) {
			int a = jobs.get(j-1).getValue() + WeightedIntervalScheduling(jobs.get(j-1).getCompatabilityIndex());
			int b = WeightedIntervalScheduling(j - 1);
			if(a==b) {
				M[j] = a;
			}
			else {
				M[j] = Math.max(a, b);
			}
		}

		return M[j];

	}
	public static void WeightedIntervalSchedulingIterative() {
		
		for(int j = 1; j <= jobs.size(); j++) {
			if(jobs.get(j-1).getValue() + M[jobs.get(j-1).getCompatabilityIndex()] == M[j-1]) {
				M[j] = jobs.get(j-1).getValue() + M[jobs.get(j-1).getCompatabilityIndex()];
				Op[j] = Op[j]+=jobs.get(j-1).jobName();
			}
			else {
			
			M[j] = Math.max(jobs.get(j-1).getValue() + M[jobs.get(j-1).getCompatabilityIndex()], M[j-1]);
			
//			Op[j] = Op[j]+=jobs.get(j-1).jobName()+ ","+jobs.get(jobs.get(j-1).getCompatabilityIndex()).jobName();
			}
		}
		

	}

	public static String ReadUserInput() {
		Scanner reader = new Scanner(System.in);
		String userInput = "";
		System.out.println("Enter your file name: ");
		try {
			userInput = reader.nextLine();

		} catch (InputMismatchException a) {
			System.out.println("Please enter an existing file name");
			ReadUserInput();
		}
		reader.close();
		return userInput;
	}

}
