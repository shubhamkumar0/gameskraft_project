import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class app{
	public static void main(String[] args) {
		// init the prob generator
		try{
     ProbabilityGenerator probabilityGenerator = new ProbabilityGeneratorImpl("probabilities.csv");
		 System.out.print(probabilityGenerator);
     //numberOfTimes : Number of times that method is called. We can have any value for the same
     Scanner myInput = new Scanner( System.in );
     System.out.print( "Enter numberOfTimes: " );
     int numberOfTimes = myInput.nextInt();
		 PrintWriter writer = new PrintWriter(new File("output.csv"));
     for(int i=0; i<numberOfTimes; i++) {
		 		StringBuilder sb = new StringBuilder();
        String nextStr = probabilityGenerator.getNextString();
        // write in the output file
				sb.append(nextStr);
				sb.append("\n");
				writer.write(sb.toString());
		 }
		 writer.close();
	 } catch (Exception e) {
	    	e.printStackTrace();
	  }
	}
}
