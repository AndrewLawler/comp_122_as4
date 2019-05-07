import java.io.*;
import java.text.DecimalFormat;

public class Analyser {
  
    String text;

    public Analyser(String text){
        this.text = text;
        run(text);   
    }

    public void run(String text){
        System.out.println("Length: "+getLength());
        double[] arr = getFrequencies();
        DecimalFormat df = new DecimalFormat("#.###");
        for(int i=0; i<getLength(); i++){
            System.out.println(text.charAt(i)+": "+getCount(text.charAt(i))+"; "+df.format(arr[i]));
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

        //String x = args[0];
        //Analyser trial = new Analyser(x);
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
        double[] Frequency = new double[getLength()];
        for(int i=0; i<getLength(); i++){
            Frequency[i] = getFrequency(text.charAt(i));
        }
        return Frequency;
    }

}