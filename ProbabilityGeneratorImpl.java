import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class ProbabilityGeneratorImpl implements ProbabilityGenerator{
  //to store the sentences
  private ArrayList<String> sentences = new ArrayList<String>();
  //to store the starting of each range of cummulative probabilities
  private ArrayList<Double> rangeStart = new ArrayList<Double>();
  private Double start=0D;

  ProbabilityGeneratorImpl(String pathToCsv) throws Exception {
    /*
    We will iterate each line and calucate the cummulative probabilities.
    The range between current probability and current cummulative probability
    will give us a range.
    [0,firstSentenceProbab), [firstSentenceProbab, CummulativeProbab)...
    This range will give us the probaility =
    len(range)/len(complete range) .
    The len(complete range is 1 here as probaility is between 0 to 1.
    We store the start index of these range in the arraylist to iterate later.
    */
    BufferedReader csvReader =
      new BufferedReader(new FileReader(pathToCsv));
    String row = "";
    while ((row = csvReader.readLine()) != null) {
      String[] data = row.split(",");
      sentences.add(data[0]);
      rangeStart.add(start);
      start+=Double.parseDouble(data[1]);
    }

    if (csvReader != null) {
      csvReader.close();
    }
  }

  public String getNextString(){
    /*
    We are generating random real numbers between 0 and 1;
    We find at where the number lies, and choose that same sentence.
    */
    Random rand = new Random();
    Double rand_idx= rand.nextDouble();
    int i=0;
    int idx = ((Collections.binarySearch(rangeStart, rand_idx))*-1)-1;
    return sentences.get(idx-1);
  }
}
