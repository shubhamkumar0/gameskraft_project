import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Random;
import java.util.ArrayList;

public class ProbabilityGeneratorImpl implements ProbabilityGenerator{
  private ArrayList<String> sentences = new ArrayList<String>();
  private ArrayList<Double> rangeStart = new ArrayList<Double>();
  private Double start=0D;
  ProbabilityGeneratorImpl(String pathToCsv) throws Exception {
    BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));

    String row = "";
    int index=0;
    while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(",");
        // Double end = start+Double.parseDouble(data[1]);
        // System.out.println(start+" , "+end);
        sentences.add(data[0]);
        rangeStart.add(start);
        start+=Double.parseDouble(data[1]);
    }

    if (csvReader != null) {
      csvReader.close();
    }
  }

  public String getNextString(){
    Random rand = new Random();
    Double rand_idx= rand.nextDouble();
    int i=0;
    for(;i<rangeStart.size();i++){
      if(rangeStart.get(i)>rand_idx){
        break;
      }
    }
    return sentences.get(i-1);
  }
}
