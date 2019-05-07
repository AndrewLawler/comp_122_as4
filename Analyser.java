import java.io.*;
import java.util.HashMap;
import java.text.DecimalFormat;

public class Analyser {
  
    String text;
    HashMap<String, Double> FrequencyMap;
    DecimalFormat df;

    public Analyser(String text){
        FrequencyMap = new HashMap<String, Double>();
        this.text = text;
        df = new DecimalFormat("#.###");
        run(text);   
    }

    public void run(String text){
        // populate hashmap
        for(int i=0; i<getLength(); i++){
            String s = String.valueOf(text.charAt(i));  
            if(FrequencyMap.containsValue(s)==false){
                FrequencyMap.put(String.valueOf(text.charAt(i)),getFrequency(text.charAt(i)));
            }
        }

        // print out
        System.out.println("Length: "+getLength());
        for (String i : FrequencyMap.keySet()) {
            char c = i.charAt(0);
            System.out.println(i+": "+getCount(c)+"; "+df.format(FrequencyMap.get(i)));
        }   

    }

    public static void main(String[] args){

        if(args.length>=1){

            int loopParam = 0;
            int i = 0;
            String output = "";
            boolean pass = false;
            
            while(loopParam<args.length){
                File file = new File(args[loopParam]);
                if(file.exists()){
                    try {
                        FileInputStream is = new FileInputStream(args[loopParam]);
                        while((i = is.read()) !=-1 ) {
                            char c = (char)i;
                            output += c;
                        }
                        loopParam++;
                        is.close();
                        pass = true;
                    } catch (IOException e) {
                        System.out.println("Error with file "+args[loopParam]);
                        pass = false;
                    }
                }
                else{
                    pass = false;
                    if(args.length==1){
                        System.out.println("File does not exist");
                    }
                    else if(args.length>1){
                        System.out.println("One or more files do not exist");
                    }
                    loopParam = args.length;
                }
            }
            if(pass==true){
                Analyser preText = new Analyser(output);
            }
        }
        else if(args.length==0){
            System.out.println("Please enter some arguments!");
        }
    }

    public int getLength(){
        int len = text.length();
        return len;
    }

    public int getCount(char c){
        int count = 0;
        for(int i=0; i<getLength(); i++){
            if(text.charAt(i)==c){
                count++;
            }   
        }
        return count;
    }

    public double getFrequency(char c){
        double count = getCount(c);
        double length = getLength();
        double Freq = (count/length);
        return Freq;
    }

    public double[] getFrequencies(){
        double[] Freq = new double[FrequencyMap.size()];
        int x = 0;
        for (String i : FrequencyMap.keySet()) {
            Freq[x] = FrequencyMap.get(i);
            x++;
        }
        return Freq;
    }

}